package com.shop.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 购物车实体类
 */

@Data
@TableName(value = "bs_cart")
public class Cart extends Model<Cart>{

	@TableId(type = IdType.AUTO)
	private int id;
	private int userId;
	private int bookId;
	private int count;

	//屏蔽book属性和数据库表中字段映射
	@TableField(exist = false)
	private Book book;

}
