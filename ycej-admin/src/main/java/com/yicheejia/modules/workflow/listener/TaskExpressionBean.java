package com.yicheejia.modules.workflow.listener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.sms.SmsSendMontnets;
import com.yicheejia.common.utils.ApiJson;
import com.yicheejia.common.utils.DateUtils;
import com.yicheejia.common.utils.HttpsClientUtil;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.order.service.YcejOrderService;
import com.yicheejia.modules.sys.service.SysDictService;
import com.yicheejia.modules.sys.service.SysUserService;
import com.yicheejia.modules.webservices.service.AppSendService;
import com.yicheejia.workflow.service.ActivitiBaseService;

import net.sf.json.JSONObject;

/**
 * activiti 表达式监听
 * 使spring监听
 * @author hunk
 *
 */
@Service
public class TaskExpressionBean implements Serializable {
    /**
     * 必须实现 Serializable
     */
    private static final long serialVersionUID = -1274370938030216159L;

    @Autowired
    SysUserService userService;
    @Autowired
    ActivitiBaseService activitiBaseService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    YcejOrderService ycejOrderService;
    @Autowired
    SmsSendMontnets smsSendMontnets;
    @Autowired 
	private AppSendService appSendService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * ${taskExpressionBean.addShopFilter(task)}
     * 
     * @param task
     */
    public void addShopFilter(DelegateTask task) {
        //查询实例
        if (task.getProcessInstanceId() != null) {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId()).singleResult();
            if (processInstance == null) {
            	logger.error("流程ID"+task.getProcessInstanceId());
            	return ;
            }
            //查询订单
            YcejOrderEntity order = ycejOrderService.selectById(processInstance.getBusinessKey());
            if (order != null && order.getShopId() != null) {
                Set<IdentityLink> set = task.getCandidates();
                String groupID = getGroupId(set);
                //查询用户组,存在用户，进行重置，不存在不处理
                List<String> user = userService.queryUserByRoleAndShop(groupID, order.getShopId() + "");
                if (user.isEmpty()) {
                	
                    logger.error("角色："+groupID+"门店ID："+order.getShopId()+"下未查询到用户");
                    throw new RRException("角色："+groupID+"下未分配用户");
                } else {
                    task.deleteCandidateGroup(groupID);
                    task.addCandidateUsers(user);
                }
            }
        }
    }
    /**
     * 消息推送
     * @param task
     * @throws Exception
     */
    public void  sendMessage(DelegateTask task) throws Exception {

        //查询实例
        if (task.getProcessInstanceId() != null) {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId()).singleResult();
            if (processInstance == null) {
            	logger.error("流程ID"+task.getProcessInstanceId());
            	return ;
            }
            
//            YcejOrderEntity order = ycejOrderService.selectById(processInstance.getBusinessKey());
            List<String> userIds = new ArrayList<>();
            List<String> userList = new ArrayList<>();
            Set<IdentityLink> set = task.getCandidates();
            String groupID = getGroupId(set);
            String name = task.getName();
            if (name.length()>=5) {
				name = name.substring(0, 5);
			}
            //用户账号
            String na=processInstance.getStartUserId();
            //根据用户账号获取用户名
            String nameuser = userService.selectNameByUsername(na);
            String time=DateUtils.formatTime2(processInstance.getStartTime());
            //表示用户分组
            if (StringUtils.isNotBlank(groupID)) {
            	userIds = userService.queryUserByRoleForPutMessage(groupID);
            	if (userIds.isEmpty()) {
                    logger.error("角色："+groupID+"下未查询到用户");
                    throw new RRException("角色："+groupID+"下未分配用户");
                } 
			}else {
				for (IdentityLink identityLink : set) {
					userList.add(identityLink.getUserId());
            	}
				userIds = userService.selectIdsByUserNames(userList);
			}
             //获取用户id
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", userIds);
	   		 jsonObject.put("titleText", "壹车e家待办任务");
//	   		 jsonObject.put("describe", "您有一条待处理任务:"+name+",请及时关注系统");
	   		 jsonObject.put("describe", "您有一笔"+name+"订单，提交人:"+nameuser+"，提交时间:"+time+"，请处理祝工作顺利");
	   		 jsonObject.put("url", WfConstants.URL_PATH);
	   		 try {
//	   			 JSONObject json=HttpsClientUtil.getInstance().sendPostRequest(url,map, jsonObject.toString());
	   			ApiJson json = appSendService.sendMessage(jsonObject.toString());
	   			 if (json.getMsgcode()!=100) {
	   				logger.info("用户不存在或设备号不存在或任务已结束");
	   			}
	   		} catch (Exception e) {
	   			logger.error("消息推送调用服务失败"+e.getMessage());
	   			throw new Exception("消息推送异常"+e.getMessage());
	   		}
        }
    
    }

    /**
     * 取第一个角色
     * 
     * @param set
     * @return
     */
    private String getGroupId(Set<IdentityLink> set) {
        for (IdentityLink identityLink : set) {
            return identityLink.getGroupId();
        }
        return null;
    }
    /**
     * 短信发送
     * @throws Exception 
     */
    public void putMessage(DelegateTask task) throws Exception{
    	//查询实例
        if (task.getProcessInstanceId() != null) {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId()).singleResult();
            if (processInstance == null) {
            	logger.error("流程ID"+task.getProcessInstanceId());
            	return ;
            }
            List<String> userIds = new ArrayList<>();
            List<String> userList = new ArrayList<>();
            Set<IdentityLink> set = task.getCandidates();
            String groupID = getGroupId(set);
            String name = task.getName();
            //用户账号
            String na=processInstance.getStartUserId();
            //根据用户账号获取用户名
            String nameuser = userService.selectNameByUsername(na);
            String time=DateUtils.formatTime(processInstance.getStartTime());
            if (name.length()>=5) {
				name = name.substring(0, 5);
			}
            //表示用户分组
            if (StringUtils.isNotBlank(groupID)) {
            	userIds = userService.queryPhoneByRoleForPutMessaging(groupID);
            	if (userIds.isEmpty()) {
                    logger.error("角色："+groupID+"下未查询到用户");
                    throw new RRException("角色："+groupID+"下未分配用户");
                } 
			}else {
				for (IdentityLink identityLink : set) {
					userList.add(identityLink.getUserId());
            	}
				userIds = userService.selectPhoneByUserNames(userList);
			}
            String mobile = userIds.toString();
            try {
				String mString = smsSendMontnets.sendmsg(mobile,"您有一笔"+name+"订单，提交人："+nameuser+"，提交时间："+time+"，请尽快登录处理，祝工作顺利");
				logger.info(mString);
			} catch (Exception e) {
				throw new Exception("短信发送失败"+e.getMessage());
			}
        }
    }
}
