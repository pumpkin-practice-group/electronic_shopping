package com.shop.shop.controller;

import com.shop.shop.entity.User;
import com.shop.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	//验证用户名是否存在
	@ResponseBody
	@RequestMapping("/checkUserName")
	public String checkUserName(String username){

		return userService.checkUser(username);
	}
	/**
	 * 用户注册
	 */
	@ResponseBody
	@RequestMapping("register")
	public String register(User user){
		System.out.println(user);
		userService.save(user);
		return "success";
	}

	/**
	 * 用户登录
	 */
	@ResponseBody
	@RequestMapping("/login")
	public String login(User user, HttpSession session){
		return userService.loginCheck(user, session);
	}

	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/book/index";
	}




}
