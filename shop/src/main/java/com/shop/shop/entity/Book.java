package com.shop.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.shop.shop.entity.enums.Category;
import com.shop.shop.entity.enums.Suit;
import lombok.Data;

import java.util.Date;

/**
 * 图书实体类
 *
 */
@Data
@TableName(value = "bs_book")
public class Book extends Model<Book> {
	@TableId(type = IdType.AUTO)//自动生成
	private int id;
	private String isbn;
	private String name;
	private String author;
	private String publisher;
	private Date publishDate;
	private double oldPrice;
	private double newPrice;
	private String authorLoc;
	private Suit suit;
	private Category category;
	private String info;
	private String imgUrl;
}
