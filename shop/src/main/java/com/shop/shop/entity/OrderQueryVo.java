package com.shop.shop.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 *
 */
@Data
public class OrderQueryVo {
	private int page;
	private int pageSize;
	private String orderNum;
	private String tradeStatus;
	private String orderBy;
	private int userId;

	//分页起始位置
	private int begin;
	//分页结束位置
	private int end;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
}