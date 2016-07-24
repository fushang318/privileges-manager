package com.lesaas.web;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lesaas.common.Constants;
import com.lesaas.common.RightsHelper;
import com.lesaas.common.Tools;
import com.lesaas.model.Menu;
import com.lesaas.model.User;
import com.lesaas.service.MenuService;
import com.lesaas.service.UserService;

@Controller
public class LoginAction {

	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	
	/**
	 * 访问登录页
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginGet(){
		return "login";
	}
	
	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView loginPost(HttpSession session,@RequestParam String loginname,@RequestParam String password){
		ModelAndView mv = new ModelAndView();
		String errInfo = "";
			User user = userService.getUserByNameAndPwd(loginname, password);
			if(user!=null){
				session.setAttribute(Constants.SESSION_USER, user);
			}else{
				errInfo = "用户名或密码有误！";
			}
		if(Tools.isEmpty(errInfo)){
			mv.setViewName("redirect:index.do");
		}else{
			mv.addObject("errInfo", errInfo);
			mv.addObject("loginname",loginname);
			mv.addObject("password",password);
			mv.setViewName("login");
		}
		return mv;
	}
	
	/**
	 * 访问系统首页
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(HttpSession session,Model model){
		User user = (User)session.getAttribute(Constants.SESSION_USER);
		List<Object[]> user1 = userService.getUserAndRoleById(user.getUserId());
		//Role role = user.getRole();
		String roleRights = user1!=null ? user1.get(0)[4].toString() : "";
		//避免每次拦截用户操作时查询数据库，以下将用户所属角色权限存入session
		session.setAttribute(Constants.SESSION_ROLE_RIGHTS, roleRights); //将角色权限存入session
		
		List<Menu> menuList = menuService.listAllMenu();
		if(Tools.notEmpty(roleRights)){
			for(Menu menu : menuList){
				menu.setHasMenu(RightsHelper.testRights(roleRights, menu.getMenuId()));
				if(menu.isHasMenu()){
					List<Menu> subMenuList = menu.getSubMenu();
					for(Menu sub : subMenuList){
						sub.setHasMenu(RightsHelper.testRights(roleRights, sub.getMenuId()));
					}
				}
			}
		}
		model.addAttribute("user", user1.get(0));
		model.addAttribute("menuList", menuList);
		return "index";
	}
	
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.removeAttribute(Constants.SESSION_USER);
		session.removeAttribute(Constants.SESSION_ROLE_RIGHTS);
		return "login";
	}
}
