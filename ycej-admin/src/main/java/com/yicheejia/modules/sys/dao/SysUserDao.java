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

package com.yicheejia.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yicheejia.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 系统用户
 * 
 *
 *
 * @date 2016年9月18日 上午9:34:11
 */
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	 /**
     * 查询用户的所有菜单ID
     */
    List<String> queryUserByShopRole(@Param("shopNO") String shopNO,@Param("roleName") String roleName);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

	/**
     * 根据门店Id和角色名查询用户名
     */
    List<String> queryUserByRoleAndShopId(@Param("shopId") String shopId,@Param("roleName") String roleName,@Param("dataFilter") Integer dataFilter);
    
    /**
     * 根据用户名批量查询用户
     */
    List<SysUserEntity> selectNamebyAccount(List list);

	/**
	 * 根据用户id集合批量查询
	 * @param list
	 * @return
	 */
	List<Map<String,Object>> queryByUserIdList(List<Long> list);
	/**
	 * 根据id集合查询name
	 * @param list
	 * @return
	 */
	List<SysUserEntity> selectNamebyId(List list);

	int deleteBatch(Long[] userIds);

	List<String> queryUserByRoleAndShopIdTo(@Param("shopId") String shopId,@Param("roleName") String roleName);

	List<String> selectIdsByUserNames(List<String> userList);

	List<String> queryUserByRoleForPutMessage(String groupID);

	List<String> queryPhoneByRoleForPutMessaging(String groupID);

	List<String> selectPhoneByUserNames(List<String> userList);

	String selectNameByUsername(String username);

	Map<String,Object> selectClient(Long userId);

	void updateBySql(String clientId,Long sysUserId);

}
