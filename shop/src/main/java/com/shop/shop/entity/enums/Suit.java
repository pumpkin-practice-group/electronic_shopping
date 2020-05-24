package com.shop.shop.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 套装枚举
 * 数据库中用1，2表示
 * 是套装为1，不是套装为2
 */
@Getter
public enum Suit {
	YES(1, "是"), NO(2, "否");
	Suit(int code, String desc){
		this.code = code;
		this.desc = desc;
	}
	@EnumValue
	private final int code;
	private final String desc;
}
