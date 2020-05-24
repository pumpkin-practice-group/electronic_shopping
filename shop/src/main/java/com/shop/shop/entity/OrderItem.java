package com.shop.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 订单
 */

@Data
@TableName(value = "bs_order_item")
public class OrderItem extends Model<OrderItem>{

	@TableId(type = IdType.AUTO)
	private int id;
	private int orderId;
	private int bookId;
	private int count;

	//图书对象
	@TableField(exist = false)
	private Book book;
}
