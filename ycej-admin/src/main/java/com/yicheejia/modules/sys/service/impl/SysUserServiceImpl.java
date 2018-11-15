/**
 *
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.yicheejia.modules.sys.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.annotation.DataFilter;
import com.yicheejia.common.annotation.SynRole.Action;
import com.yicheejia.common.annotation.SynUser;
import com.yicheejia.common.utils.Constant;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.common.utils.Query;
import com.yicheejia.common.utils.QueryUtils;
import com.yicheejia.modules.shop.entity.YcejShopEntity;
import com.yicheejia.modules.sys.dao.SysUserDao;
import com.yicheejia.modules.sys.dao.SysUserRoleDao;
import com.yicheejia.modules.sys.entity.SysDeptEntity;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.service.*;
import com.yicheejia.modules.sys.shiro.ShiroUtils;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 *
 *
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private SysUserShopService sysUserShopService;
	@Autowired
	private SysPartnerShopService sysPartnerShopService;
	@Autowired
	private SysConfigService sysConfigService;
	@Resource
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	@DataFilter(subDept = true, user = false)
	public LayuiPage queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");
		EntityWrapper<SysUserEntity> wrapper = new EntityWrapper<>();
		
		if(username!=null && !"".equals(username.toString())){
            //wrapper.like("shop_no",shopNo.toString());
            String queryStr = QueryUtils.toFuzzyQueryStr(username.toString());
            String filterSql = "(username like '" + queryStr+"' or name like '"+queryStr+"')";
            wrapper.addFilter(filterSql);
        }
		wrapper.addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER));
		Page<SysUserEntity> page = this.selectPage(
			new Query<SysUserEntity>(params).getPage(),
			wrapper
		);

		for(SysUserEntity sysUserEntity : page.getRecords()){
			SysDeptEntity sysDeptEntity = sysDeptService.selectById(sysUserEntity.getDeptId());
			sysUserEntity.setDeptName(sysDeptEntity.getName());

			Long userId = sysUserEntity.getUserId();
			List<String> roleNameList = sysUserRoleDao.queryRoleNameListByUserId(userId);
			//sysUserEntity.setRoleNameList(roleNameList);
			StringBuilder sb = new StringBuilder();

			for(String roleName : roleNameList){
				sb.append(roleName).append(",");
			}
			int lastIndex = sb.toString().lastIndexOf(",");
			String userRole;
			if(lastIndex != -1){
				userRole = sb.toString().substring(0,sb.toString().lastIndexOf(","));
			}else{
				userRole = sb.toString();
			}
			sysUserEntity.setRoleNames(userRole);
		}

		return new LayuiPage(page.getRecords(), page.getTotal());
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setSalt(salt);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		this.insert(user);
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
		//保存用户与门店关系
		sysUserShopService.saveOrUpdate(user.getUserId(),user.getShopIdList());
		//保存合伙人与门店关系
		sysPartnerShopService.saveOrUpdate(user.getUserId(),user.getPartShopIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
		}
		this.updateById(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
		//保存用户与门店关系
		sysUserShopService.saveOrUpdate(user.getUserId(),user.getShopIdList());
		//保存合伙人与门店关系
		sysPartnerShopService.saveOrUpdate(user.getUserId(),user.getPartShopIdList());

	}


	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new EntityWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
    }
	
	/**
     * 通过 角色，门店，查询对应用户
     * @param roleName
     * @param shopNO
     */
    public List<String> queryUserByRoleAndShop(String roleName,String shopNO) {
        return baseMapper.queryUserByShopRole(shopNO, roleName);
    }

    /**
     * 通过 角色，门店，查询对应用户
     * @param
     * @param shopId
     */
	@Override
	public List<String> queryUserByRoleAndShopId(String shopId,Integer dataFilter) {
//		List<String> list = new ArrayList<String>();
		//查询调拨确认人角色：参数管理中设置
//		String roleNames = sysConfigService.getValueForAllot("roleNamesForAllot");
		
//		String[] roleName = roleNames.split(";");
		List<String> queryList = null;
		if(dataFilter==1) {
			//查询调拨确认人角色：参数管理中设置
			String roleNames = sysConfigService.getValueForAllot("roleNamesForAllot");
			queryList = baseMapper.queryUserByRoleAndShopId(shopId, roleNames,dataFilter);
		}
		else if(dataFilter==0) {
			//查询调拨确认人角色：参数管理中设置
			String roleNames = sysConfigService.getValueForAllot("roleNamesForAllot1");
			queryList = baseMapper.queryUserByRoleAndShopId(shopId, roleNames,dataFilter);
		}
//		for(String roleNameParm : roleName) {
//			 List<String> queryList = baseMapper.queryUserByRoleAndShopId(shopId, roleNameParm,dataFilter);
//			 list.addAll(queryList);
//		}
		return queryList;
	}
	
	/**
	 * 根据用户ID查询用户名
	 * @param list
	 * @return
	 */
	public List<SysUserEntity> selectNamebyAccount(List list) {
	    
	    List<SysUserEntity> sysList = baseMapper.selectNamebyAccount(list);
	    
	    return sysList;
	}
	
	/**
	 * 根据账号List中username 获取 name
	 * @param userList
	 * @return
	 */
    public Map getUserName(List userList) {
        Map userMap = new HashMap<String, String>();
        if (userList != null && userList.size() > 0) {
            List<SysUserEntity> sysUserList = selectNamebyAccount(userList);
            for (SysUserEntity sysUserEntity : sysUserList) {
                userMap.put(sysUserEntity.getUsername(), sysUserEntity.getName());
            }
        }
        return userMap;
    }

	@Override
	public int deleteBatch(Long[] userIds) {
		return baseMapper.deleteBatch(userIds);
	}

	/**
	 * 根据用户ID查询用户名
	 * @param list
	 * @return
	 */
	public List<SysUserEntity> selectNamebyId(List list) {
	    
	    List<SysUserEntity> sysList = baseMapper.selectNamebyId(list);
	    
	    return sysList;
	}
	
    /**
	 * 根据账号List 中id 获取 name
	 * @param userList
	 * @return
	 */
    public Map getName(List userList) {
        Map userMap = new HashMap<String, String>();
        if (userList != null && userList.size() > 0) {
            List<SysUserEntity> sysUserList = selectNamebyId(userList);
            for (SysUserEntity sysUserEntity : sysUserList) {
                userMap.put(sysUserEntity.getUserId()+"", sysUserEntity.getName());
            }
        }
        return userMap;
    }

	@Override
	public List<String> queryUserByRoleAndShopIdTo(String shopId) {
		List<String> queryList = new ArrayList<String>();

		String roleNames = sysConfigService.getValueForAllot("roleNamesForAllot");
		queryList = baseMapper.queryUserByRoleAndShopIdTo(shopId, roleNames);
		return queryList;
	}

	@Override
	public List<String> selectIdsByUserNames(List<String> userList) {
		return baseMapper.selectIdsByUserNames(userList);
	}

	@Override
	public List<String> queryUserByRoleForPutMessage(String groupID) {
		return baseMapper.queryUserByRoleForPutMessage(groupID);
	}

	@Override
	public List<String> queryPhoneByRoleForPutMessaging(String groupID) {
		return baseMapper.queryPhoneByRoleForPutMessaging(groupID);
	}

	@Override
	public List<String> selectPhoneByUserNames(List<String> userList) {
		// TODO Auto-generated method stub
		return baseMapper.selectPhoneByUserNames(userList);
	}

	@Override
	public String selectNameByUsername(String na) {
		// TODO Auto-generated method stub
		return baseMapper.selectNameByUsername(na);
	}

	@Override
	public void updateBySql(String clientId,Long sysUserId) {
		baseMapper.updateBySql(clientId,sysUserId);
	}

}
