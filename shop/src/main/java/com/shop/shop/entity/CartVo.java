package com.shop.shop.entity;

import lombok.Data;

@Data
public class CartVo {
	private int id;
	private int userId;
	private int bookId;
	private String bookName;
	private String imgUrl;
	private double newPrice;
	private int count;
}
