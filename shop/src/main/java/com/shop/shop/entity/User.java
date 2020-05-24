package com.shop.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体类
 */
@Data
@TableName(value = "bs_user")
public class User {
	@TableId(type = IdType.AUTO)
	private int id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String company;
}
