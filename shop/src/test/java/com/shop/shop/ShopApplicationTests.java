package com.shop.shop;

import com.shop.shop.mapper.CartMapper;
import com.shop.shop.mapper.OrderMapper;
import com.shop.shop.service.BookService;
import com.shop.shop.utils.OrderUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopApplicationTests {

	@Autowired
	private BookService bookService;
	@Autowired
	private CartMapper cartMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Test
	public void findBookList(){
		bookService.list().forEach(System.out::print);
	}

	@Test
	public void findCartList(){
		cartMapper.findCartListByUserId(10).forEach(System.out::println);
	}

//	@Test
//	public void findOrderList(){
//		orderMapper.findOrderAndOrderDetailListByUser(10);
//	}

	@Test
	public void testCreatOrderNo(){
		System.out.println(OrderUtils.createOrderNum());
	}
}
