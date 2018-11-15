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


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yicheejia.common.utils.Constant;
import com.yicheejia.common.utils.MapUtils;
import com.yicheejia.modules.sys.dao.SysMenuDao;
import com.yicheejia.modules.sys.entity.NavMenuEntity;
import com.yicheejia.modules.sys.entity.SysMenuEntity;
import com.yicheejia.modules.sys.service.SysMenuService;
import com.yicheejia.modules.sys.service.SysRoleMenuService;
import com.yicheejia.modules.sys.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuEntity> menuList = queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenuEntity> userMenuList = new ArrayList<>();
		for(SysMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId) {
		return baseMapper.queryListParentId(parentId);
	}

	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return baseMapper.queryNotButtonList();
	}

	@Override
	public List<NavMenuEntity> getNavMenuList(Long userId) {
		//导航菜单ID列表
		List<Long> menuIdList = null;
		//非系统管理员
		if(userId != Constant.SUPER_ADMIN){
			menuIdList = sysUserService.queryAllMenuId(userId);
		}

		//查询根菜单列表
		List<NavMenuEntity> navList = queryNavListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(navList, menuIdList);

		return navList;
	}

	@Override
	public void delete(Long menuId){
		//删除菜单
		this.deleteById(menuId);
		//删除菜单与角色关联
		sysRoleMenuService.deleteByMap(new MapUtils().put("menu_id", menuId));
	}

	/**
	 * 查询导航菜单列表
	 */
	private List<NavMenuEntity> queryNavListParentId(Long parentId, List<Long> menuIdList) {
		//查询根菜单列表
		List<SysMenuEntity> menuList = queryListParentId(parentId, menuIdList);

		List<NavMenuEntity> navList = new ArrayList<>(menuList.size());
		for(SysMenuEntity menuEntity : menuList){
			NavMenuEntity nav = new NavMenuEntity();
			nav.setId(menuEntity.getMenuId());
			nav.setPid(menuEntity.getParentId());
			nav.setIcon(menuEntity.getIcon());
			nav.setTitle(menuEntity.getName());
			nav.setUrl(menuEntity.getUrl());
			nav.setType(menuEntity.getType());
			nav.setSpread(menuEntity.isSpread());

			navList.add(nav);
		}
		return navList;
	}

	/**
	 * 递归
	 */
	private List<NavMenuEntity> getMenuTreeList(List<NavMenuEntity> navList, List<Long> menuIdList){
		List<NavMenuEntity> subMenuList = new ArrayList<>();

		for(NavMenuEntity nav : navList){
			//目录
			if(nav.getType() == Constant.MenuType.CATALOG.getValue()){
				nav.setChildren(getMenuTreeList(queryNavListParentId(nav.getId(), menuIdList), menuIdList));
			}
			subMenuList.add(nav);
		}

		return subMenuList;
	}
}
