package com.lesaas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lesaas.dao.MenuDao;
import com.lesaas.mapper.MenuMapper;
import com.lesaas.model.Menu;



@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)//所有方法不加事物
public class MenuService {
	@Autowired
	private MenuDao menuDao;
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
			return menuDao.listSubMenuByParentId(parentId);
	}

	public List<Menu> listAllParentMenu() {
		return menuDao.listAllParentMenu();
	}

	public List<Menu> listAllSubMenu() {
		return menuDao.listAllSubMenu();
	}
	
	public void deleteMenuById(Integer menuId) {
		menuDao.deleteMenuById(menuId);
	}

	public Menu getMenuById(Integer menuId) {
		return menuDao.getMenuById(menuId);
	}
	
	public void saveMenu(Menu menu) {
		if(menu.getMenuId()!=null && menu.getMenuId().intValue()>0){
			menuDao.updateMenu(menu);
		}else{
			menuDao.insertMenu(menu);
		}
	}
}
