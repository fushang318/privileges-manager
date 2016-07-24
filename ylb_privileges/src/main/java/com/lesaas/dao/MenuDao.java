package com.lesaas.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.lesaas.model.Menu;

@Repository
public class MenuDao extends BaseDao<Menu>{
	

	protected Logger log = Logger.getLogger(MenuDao.class.getName());

	
	public List<Menu> listSubMenuByParentId(Integer parentId){
		List<Menu> menus = this.find("from Menu where parent_id = ?",parentId);
		return menus;
	}


	public List<Menu> listAllParentMenu(){
		List<Menu> menus = this.find("from Menu where parent_id is null");
		return menus;
	}

	public List<Menu> listAllSubMenu(){
		List<Menu> menus = this.find("from Menu where parent_id is not null");
		return menus;
	}
	
	public Menu getMenuById(Integer menuId){
		Menu menu = this.findUnique("from Menu where menu_id=?",menuId);
		return menu;
	}
	
	public void insertMenu(Menu menu){
		this.batchExecuteSql("insert into bas_menu (menu_name,menu_url,parent_id) values ('"+menu.getMenuName()+"','"+menu.getMenuUrl()+"',"+menu.getParentId()+")");
	}
	
	public void updateMenu(Menu menu){
		this.batchExecuteSql("update bas_menu set menu_name='"+menu.getMenuName()+"',menu_url='"+menu.getMenuUrl()+"',parent_id="+menu.getParentId()+" where menu_id="+menu.getMenuId());
	}
	
	public void deleteMenuById(Integer menuId){
		this.batchExecuteSql("delete from bas_menu where menu_id="+menuId+" or parent_id="+menuId);
	}
}
