package com.shop.shop.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.shop.entity.User;
import com.shop.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 验证用户是否存在
	 * @param username
	 * @return
	 */
	public String checkUser(String username){
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username",username);
		User user = userMapper.selectOne(queryWrapper);
		if(user == null){
			return "101";
			//用户不存在，可以进行注册
		}
		else{
			return "102";
			//用户已存在，不可注册
		}
	}


	/**
	 * 登录验证
	 * @param loginUser
	 * @param session
	 * @return
	 */
	public String loginCheck(User loginUser, HttpSession session){
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username",loginUser.getUsername());
		User user = userMapper.selectOne(queryWrapper);

		if(user == null){
			return "101";//用户不存在
		}
		else{
			//判断密码是否正确
			if(loginUser.getPassword().equals(user.getPassword())){
				session.setAttribute("user",user);
				return "100";//密码正确
			}
			else{
				return "102";//密码不正确
			}
		}

	}
}
