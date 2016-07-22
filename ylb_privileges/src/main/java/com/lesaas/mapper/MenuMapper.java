package com.lesaas.mapper;

import java.util.List;

import com.lesaas.model.Menu;


public interface MenuMapper {

	List<Menu> listSubMenuByParentId(Integer parentId);

	List<Menu> listAllParentMenu();

	List<Menu> listAllSubMenu();
	
	Menu getMenuById(Integer menuId);
	
	void insertMenu(Menu menu);
	
	void updateMenu(Menu menu);
	
	void deleteMenuById(Integer menuId);
}
