package com.yicheejia.modules.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.constants.WfConstants;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.ConfigUtil;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.Query;
import com.yicheejia.modules.order.controller.YcejOrderController;
import com.yicheejia.modules.order.dao.OrderFileDao;
import com.yicheejia.modules.order.entity.OrderFileEntity;
import com.yicheejia.modules.order.entity.YcejOrderDetailEntity;
import com.yicheejia.modules.order.entity.YcejOrderEntity;
import com.yicheejia.modules.order.service.OrderFileService;
import com.yicheejia.modules.order.service.YcejOrderService;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.shiro.ShiroUtils;
import com.yicheejia.modules.upload.entity.YcejCusidImgEntity;
import com.yicheejia.modules.upload.entity.YcejFileEntity;

import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition.Undefined;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service("orderFileService")
public class OrderFileServiceImpl extends ServiceImpl<OrderFileDao, OrderFileEntity> implements OrderFileService {
	@Autowired
	private OrderFileDao orderFileDao;
	@Autowired
    private YcejOrderService ycejOrderService;
	//日志
    protected Logger logger = LoggerFactory.getLogger(OrderFileService.class);
    @Override
    public LayuiPage queryPage(Map<String, Object> params) {
        Page<OrderFileEntity> page = this.selectPage(
                new Query<OrderFileEntity>(params).getPage(),
                new EntityWrapper<OrderFileEntity>()
        );

        return new LayuiPage(page.getRecords(), page.getTotal());
    }
    @Override
	public void insertReturnId(YcejFileEntity ycejFileEntity,Map<String, Object> params) {
		
    	String customerId = params.get("customerId")+"";
		if(StringUtils.isBlank(customerId) || "undefined".equals(customerId)){
			throw new RRException("请先暂存！");
		}
		/*YcejOrderEntity order = ycejOrderService.selectById(customerId);
		Map<String, Object> params1 = new HashMap<String, Object>();
    	if(StringUtils.isNotBlank(order.getId()) && "01".equals(order.getStatus())){
    		params1.put("orderId", customerId);
			params1.put("fileType", "19");//订车协议照片
			List<OrderFileEntity> list = queryList(params1);
			if(list.size() > 0){
			}else{
				logger.error("请上传订车协议照片！");
				throw new RRException("请上传订车协议照片！");
			}
		}*/
    	SysUserEntity user=ShiroUtils.getUserEntity();
		OrderFileEntity orderFileEntity=new OrderFileEntity();
		try {
			String fileType = params.get("fileType").toString();
			orderFileEntity.setImgName(ycejFileEntity.getFileName());
			orderFileEntity.setOrderId(customerId);
			orderFileEntity.setFileId(ycejFileEntity.getFileId());
			orderFileEntity.setInsertId(user.getUserId().intValue());
			orderFileEntity.setFileType(fileType);
			params.put("orderId", customerId);
			params.put("fileType", fileType);
			List<OrderFileEntity> list = queryList(params);
			String[] flag = WfConstants.fileType; 
			if(list.size()>0){
				String type = list.get(0).getFileType();
				if(Arrays.asList(flag).contains(type)){//转账凭证可上传多条
					baseMapper.insertReturnId(orderFileEntity);	
				}else{
					orderFileEntity.setOrderFileId(list.get(0).getOrderFileId());//同一个订单号，同一个附件类型只让上传一次，后面都更新
					baseMapper.updateById(orderFileEntity);
				}
				/*for(OrderFileEntity file : list){
				}*/
			}else{
				baseMapper.insertReturnId(orderFileEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("上传错误！"+e.getMessage());
			throw new RRException("上传错误！");
		}
	}
	@Override
	public List<OrderFileEntity> queryList(Map<String, Object> params) {
		List<OrderFileEntity> list = orderFileDao.queryList(params);
		return list;
	}
	/**
	 * 附件删除
	 */
	@Override
	public void delClassesFile(Map<String, Object> params) {
		try {
			//先删除附件
			String filePath = params.get("filePath") + "";
			String orderFileId = params.get("orderFileId") + "";
			//String fileUrl = request.getSession().getServletContext().getRealPath("\\" + filePath);// 得到上传服务器的物理路径
			Configuration config = ConfigUtil.getConfig();
			String url = config.getString("filePath")+ File.separator ;
			String fileUrl = url + filePath;
			File deleteFile = new File(fileUrl);
			if(StringUtils.isNotBlank(orderFileId)){
				if(deleteFile.exists()){
					deleteFile.delete();
				}
				//在删除附件url
				baseMapper.deleteById(orderFileId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除失败！"+e.getMessage());
			throw new RRException("删除失败！");
		}
	}
}
