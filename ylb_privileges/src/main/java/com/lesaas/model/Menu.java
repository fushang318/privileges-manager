package com.lesaas.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "bas_menu")
public class Menu {
	private Integer menuId;
	private String menuName;
	private String menuUrl;
	private Integer parentId;
	
	private Menu parentMenu;
	private List<Menu> subMenu;
	
	private boolean hasMenu = false;
	@Id
	@GeneratedValue
	@Column(name="menu_id")
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	@Column(name="menu_name")
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Column(name="menu_url")
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	@Column(name="parent_id")
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	@Transient
	public Menu getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	@Transient
	public List<Menu> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}
	@Transient
	public boolean isHasMenu() {
		return hasMenu;
	}
	@Transient
	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}
}
