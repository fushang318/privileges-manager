package com.lesaas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lesaas.mapper.MenuMapper;
import com.lesaas.model.Menu;



@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)//所有方法不加事物
public class MenuService {
	@Autowired
	private MenuMapper menuMapper;

	public List<Menu> listAllMenu() {
			List<Menu> menus = this.listAllParentMenu();
			for(Menu menu : menus){
				List<Menu> subList = this.listSubMenuByParentId(menu.getMenuId());
				menu.setSubMenu(subList);
			}
			return menus;
	}

	private List<Menu> listSubMenuByParentId(Integer parentId) {
			return menuMapper.listSubMenuByParentId(parentId);
	}

	private List<Menu> listAllParentMenu() {
		return menuMapper.listAllParentMenu();
	}

	public List<Menu> listAllSubMenu() {
		return menuMapper.listAllSubMenu();
	}
}
