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

package com.yicheejia.modules.sys.controller;


import com.yicheejia.common.annotation.SynUser;
import com.yicheejia.modules.sys.service.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.yicheejia.common.annotation.SysLog;
import com.yicheejia.common.exception.RRException;
import com.yicheejia.common.utils.Constant;
import com.yicheejia.common.utils.ExcelUtils;
import com.yicheejia.common.utils.LayuiPage;
import com.yicheejia.common.utils.R;
import com.yicheejia.common.validator.Assert;
import com.yicheejia.common.validator.ValidatorUtils;
import com.yicheejia.common.validator.group.AddGroup;
import com.yicheejia.common.validator.group.UpdateGroup;
import com.yicheejia.modules.sys.entity.SysRoleEntity;
import com.yicheejia.modules.sys.entity.SysUserEntity;
import com.yicheejia.modules.sys.excel.SysUserBean;
import com.yicheejia.modules.sys.shiro.ShiroUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 *
 *
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserShopService sysUserShopService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysPartnerShopService sysPartnerShopService;

	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public LayuiPage list(@RequestParam Map<String, Object> params){
		LayuiPage page = sysUserService.queryPage(params);

		return page;
	}

	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public R info() {
		return R.ok().put("user", getUser());
	}

	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@RequestMapping("/password")
	public R password(String password, String newPassword) {
		Assert.isBlank(newPassword, "新密码不为能空");

		//原密码
		password = new Sha256Hash(password, getUser().getSalt()).toHex();
		//新密码
		newPassword = new Sha256Hash(newPassword, getUser().getSalt()).toHex();
				
		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return R.error("原密码不正确");
		}
		
		return R.ok();
	}

	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId) {
		SysUserEntity user = sysUserService.selectById(userId);

		// 获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);

		//获取用户所属的门店列表
		List<Integer> shopIdList = sysUserShopService.queryShopIdList(userId);
		user.setShopIdList(shopIdList);

		//获取合伙人查看门店列表
        List<Integer> partShopIdList = sysPartnerShopService.queryShopIdList(userId);
        user.setPartShopIdList(partShopIdList);

		return R.ok().put("user", user);
	}

	/**
	 * 保存用户
	 */
	@Transactional
	@SysLog("保存用户")
	@SynUser(value = SynUser.Action.ADD)
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user) {
		ValidatorUtils.validateEntity(user, AddGroup.class);

		sysUserService.save(user);

		//
		return R.ok(SynUser(user));
	}

	/**
	 * 修改用户
	 */
	@Transactional
	@SysLog("修改用户")
	@SynUser(value = SynUser.Action.UPDATE)
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user) {
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		sysUserService.update(user);

		return R.ok(SynUser(user));
	}

	/**
	 * 修改状态
	 */
	@SysLog("修改状态")
	@RequestMapping("/status")
	@RequiresPermissions("sys:user:update")
	public R status(Long userId, Integer status){
		if(userId == null){
			throw new RRException("用户ID不能为空");
		}
		if(status == null){
			throw new RRException("状态不能为空");
		}

		if(userId == Constant.SUPER_ADMIN){
			throw new RRException("超级管理状态，不能修改");
		}

		SysUserEntity user = new SysUserEntity();
		user.setUserId(userId);
		user.setStatus(status);
		sysUserService.updateById(user);

		return R.ok();
	}

	/**
	 * 修改状态
	 */
	@SysLog("修改数据过滤")
	@RequestMapping("/dataFilter")
	@RequiresPermissions("sys:user:update")
	public R dataFilter(Long userId,Integer dataFilter){
		if(userId == null){
			throw new RRException("用户ID不能为空");
		}
		if(dataFilter == null){
			throw new RRException("状态不能为空");
		}

		if(userId == Constant.SUPER_ADMIN){
			throw new RRException("超级管理状态，不能修改");
		}

		SysUserEntity user = new SysUserEntity();
		user.setUserId(userId);
		user.setDataFilter(dataFilter);
		sysUserService.updateById(user);

		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds) {
		if (ArrayUtils.contains(userIds, 1L)) {
			return R.error("系统管理员不能删除");
		}

		if (ArrayUtils.contains(userIds, getUserId())) {
			return R.error("当前用户不能删除");
		}

		//sysUserService.deleteBatchIds(Arrays.asList(userIds));
		sysUserService.deleteBatch(userIds);

		return R.ok();
	}
	
	/**
	 * 导出
	 */
	@RequestMapping("/export")
	@RequiresPermissions("sys:user:export")
	public void export(@RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
		LayuiPage page = sysUserService.queryPage(params);

		ExcelUtils.exportExcelToTarget(response, "用户管理", page.getData(), SysUserBean.class);
	}
	/**
	 * 同步更新封装update信息
	 * @param user
	 */
	private Map SynUser(SysUserEntity user) {
	    Map map = new HashMap();
	    if (!user.getRoleIdList().isEmpty()) {
	        List<SysRoleEntity> rolelist = sysRoleService.selectBatchIds(user.getRoleIdList());
	        if (rolelist != null && rolelist.size() > 0) {
	            StringBuffer sb = new StringBuffer();
	            for (SysRoleEntity sysRoleEntity : rolelist) {
	                sb.append(sysRoleEntity.getRoleName()).append("&");
	            }
	            map.put("user", user.getUsername());
	            map.put("role", sb.toString());
	            return map;
	        }
	    }
	    return map;
	}
}
