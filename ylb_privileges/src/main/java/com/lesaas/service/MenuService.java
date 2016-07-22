package com.lesaas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lesaas.mapper.MenuMapper;
import com.lesaas.model.Menu;



@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)//所有方法不加事物
public class MenuService {
	
	private MenuMapper menuMapper;

	public MenuMapper getMenuMapper() {
		return menuMapper;
	}

	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	public List<Menu> listAllMenu() {
			List<Menu> menus = this.listAllParentMenu();
			for(Menu menu : menus){
				List<Menu> subList = this.listSubMenuByParentId(menu.getMenuId());
				menu.setSubMenu(subList);
			}
			return menus;
	}

	public List<Menu> listSubMenuByParentId(Integer parentId) {
			return menuMapper.listSubMenuByParentId(parentId);
	}

	public List<Menu> listAllParentMenu() {
		return menuMapper.listAllParentMenu();
	}

	public List<Menu> listAllSubMenu() {
		return menuMapper.listAllSubMenu();
	}
	
	public void deleteMenuById(Integer menuId) {
		menuMapper.deleteMenuById(menuId);
	}

	public Menu getMenuById(Integer menuId) {
		return menuMapper.getMenuById(menuId);
	}
	
	public void saveMenu(Menu menu) {
		if(menu.getMenuId()!=null && menu.getMenuId().intValue()>0){
			menuMapper.updateMenu(menu);
		}else{
			menuMapper.insertMenu(menu);
		}
	}
}
