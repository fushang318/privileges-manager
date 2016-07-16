package com.lesaas.web;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lesaas.common.Constants;
import com.lesaas.common.Tools;
import com.lesaas.model.User;
import com.lesaas.service.UserService;

@Controller
public class LoginAction {

	@Autowired
	private UserService userService;
	
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
	public ModelAndView loginPost(HttpSession session,@RequestParam String loginname,@RequestParam String password,@RequestParam String code){
		String sessionCode = (String)session.getAttribute(Constants.SESSION_SECURITY_CODE);
		ModelAndView mv = new ModelAndView();
		String errInfo = "";
		if(Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)){
			User user = userService.getUserByNameAndPwd(loginname, password);
			if(user!=null){
				session.setAttribute(Constants.SESSION_USER, user);
				session.removeAttribute(Constants.SESSION_SECURITY_CODE);
			}else{
				errInfo = "用户名或密码有误！";
			}
		}else{
			errInfo = "验证码输入有误！";
		}
		if(Tools.isEmpty(errInfo)){
			mv.setViewName("redirect:index.jsp");
		}else{
			mv.addObject("errInfo", errInfo);
			mv.addObject("loginname",loginname);
			mv.addObject("password",password);
			mv.setViewName("login");
		}
		return mv;
	}
}
