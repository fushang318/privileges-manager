package com.lesaas.web;

import java.io.PrintWriter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.lesaas.model.Role;
import com.lesaas.model.User;
import com.lesaas.service.RoleService;
import com.lesaas.service.UserService;



@Controller
@RequestMapping(value="/user")
public class UserAction {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping
	public ModelAndView list(User user){
		List<User> userList = userService.listPageUser(user);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users");
		mv.addObject("userList", userList);
		mv.addObject("user", user);
		return mv;
	}
	
	@RequestMapping(value="/add")
	public String toAdd(Model model){
		List<Role> roleList = roleService.listAllRoles();
		model.addAttribute("roleList", roleList);
		return "user_info";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveUser(User user){
		ModelAndView mv = new ModelAndView();
		if(user.getUserId()==null || user.getUserId().intValue()==0){
			if(userService.insertUser(user)==false){
				mv.addObject("msg","failed");
			}else{
				mv.addObject("msg","success");
			}
		}else{
			userService.updateUserBaseInfo(user);
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView toEdit(@RequestParam int userId){
		ModelAndView mv = new ModelAndView();
		User user = userService.getUserById(userId);
		List<Role> roleList = roleService.listAllRoles();
		mv.addObject("user", user);
		mv.addObject("roleList", roleList);
		mv.setViewName("user_info");
		return mv;
	}
	
	@RequestMapping(value="/delete")
	public void deleteUser(@RequestParam int userId,PrintWriter out){
		userService.deleteUser(userId);
		out.write("success");
		out.close();
	}
	
}
