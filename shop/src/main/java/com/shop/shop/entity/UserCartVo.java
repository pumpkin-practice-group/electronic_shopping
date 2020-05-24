package com.shop.shop.entity;

import lombok.Data;

/**
 * 用户购物车信息展示对象
 */
@Data
public class UserCartVo {

	private int num;
	private double totalPrice;
}
