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

package com.yicheejia.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.PageUtils;
import com.yicheejia.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 *
 *
 * @date 2016年9月18日 上午9:43:39
 */
public interface SysUserService extends IService<SysUserEntity> {

	LayuiPage queryPage(Map<String, Object> params);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);
	
	/**
	 * 保存用户
	 */
	void save(SysUserEntity user);
	
	/**
	 * 修改用户
	 */
	void update(SysUserEntity user);

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	boolean updatePassword(Long userId, String password, String newPassword);
	
	/**
	 * 通过 角色，门店，查询对应用户
	 * @param roleName
	 * @param shopNO : 门店查询传入为ID ，故此处查询也为ID
	 */
	List<String> queryUserByRoleAndShop(String roleName,String shopNO) ;
	
	/**
	 * 通过 角色，门店，查询对应用户
	 * @param
	 * @param shopId
	 */
	List<String> queryUserByRoleAndShopId(String shopId,Integer dataFilter) ;
	
	   /**
     * 根据用户ID查询用户名
     * @param list
     * @return
     */
    List<SysUserEntity> selectNamebyAccount(List list) ;
    /**
     * 根据账号List 获取 名字
     * @param userList
     * @return
     */
    Map getUserName(List userList);
    /**
     * 根据账号List 获取 名字
     * @param userList
     * @return
     */
    Map getName(List<String> userList);

    int deleteBatch(Long[] userIds);

	/**
	 * 通过 角色，门店，查询对应用户
	 * @param
	 * @param shopId
	 */
	List<String> queryUserByRoleAndShopIdTo(String shopId);
	/**
	 * 通过用户账号获取用户主键
	 * @param userList
	 * @return
	 */
	List<String> selectIdsByUserNames(List<String> userList);
	/**
	 * 根据角色获取用户列表 for 发送消息
	 * @param groupID
	 * @return
	 */
	List<String> queryUserByRoleForPutMessage(String groupID);
	/**
	 * 根据角色获取电话列表 for 发送短信
	 * @param groupID
	 * @return
	 */
	List<String> queryPhoneByRoleForPutMessaging(String groupID);
	/**
	 * 根据用户账号获取电话号码
	 * @param userList
	 * @return
	 */
	List<String> selectPhoneByUserNames(List<String> userList);
	/**
	 * 根据用户账号获取用户名
	 * @param na
	 * @return
	 */
	String selectNameByUsername(String na);

	/**
	 * 
	 * @param sysUserEntity
	 */
	void updateBySql(String clientId,Long  sysUserId);
}
