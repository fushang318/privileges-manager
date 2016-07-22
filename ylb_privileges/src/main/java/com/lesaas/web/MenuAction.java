package com.lesaas.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lesaas.model.Menu;
import com.lesaas.service.MenuService;


@Controller
@RequestMapping(value="/menu")
public class MenuAction {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping
	public String list(Model model){
		List<Menu> menuList = menuService.listAllParentMenu();
		model.addAttribute("menuList", menuList);
		return "menus";
	}
	
	@RequestMapping(value="/add")
	public String toAdd(Model model){
		List<Menu> menuList = menuService.listAllParentMenu();
		model.addAttribute("menuList", menuList);
		return "menus_info";
	}
	
	@RequestMapping(value="/edit")
	public String toEdit(@RequestParam Integer menuId,Model model){
		Menu menu = menuService.getMenuById(menuId);
		model.addAttribute("menu", menu);
		if(menu.getParentId()!=null && menu.getParentId().intValue()>0){
			List<Menu> menuList = menuService.listAllParentMenu();
			model.addAttribute("menuList", menuList);
		}
		return "menus_info";
	}
	
	@RequestMapping(value="/save")
	public String save(Menu menu,Model model){
		menuService.saveMenu(menu);
		model.addAttribute("msg", "success");
		return "save_result";
	}
	
	@RequestMapping(value="/sub")
	public void getSub(@RequestParam Integer menuId,HttpServletResponse response){
		List<Menu> subMenu = menuService.listSubMenuByParentId(menuId);
		JSONArray arr = JSONArray.fromObject(subMenu);
		PrintWriter out;
		try {
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String json = arr.toString();
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/del")
	public void delete(@RequestParam Integer menuId,PrintWriter out){
		menuService.deleteMenuById(menuId);
		out.write("success");
		out.flush();
		out.close();
	}
}
