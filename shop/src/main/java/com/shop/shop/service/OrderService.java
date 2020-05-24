package com.shop.shop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.shop.entity.*;
import com.shop.shop.mapper.OrderMapper;
import com.shop.shop.utils.OrderUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private CartService cartService;

	/**
	 * 购买
	 * @param cartVos
	 * @param addrId
	 * @param session
	 * @return
	 */
	public String buy(List<CartVo> cartVos, int addrId, HttpSession session){

		//1.生成订单表记录
		Order order = new Order();
		order.setAddressId(addrId);//地址id
		User user = (User) session.getAttribute("user");
		order.setUserId(user.getId());//用户id
		order.setCreateDate(new Date());//创建时间
		order.setOrderNum(OrderUtils.createOrderNum());//生成自定义的订单编号（年月日+第几单）
		order.setOrderStatus("1");//订单状态（未付款、已付款等）
		orderMapper.insert(order);

		//2.生成订单明细表记录
		List<OrderItem> orderItems = new ArrayList<>();
		List<Integer> cartIds = new ArrayList<>();
		for(CartVo cart : cartVos){
			OrderItem orderItem = new OrderItem();
			orderItem.setBookId(cart.getBookId());
			orderItem.setCount(cart.getCount());
			orderItem.setOrderId(order.getId());
			orderItems.add(orderItem);
			cartIds.add(cart.getId());
		}
		orderItemService.saveBatch(orderItems);

		//3.删除购物车表中的记录
		cartService.removeByIds(cartIds);

		return "success";

	}

	/**
	 * 查询用户订单
	 * @param userId
	 * @param orderQueryVo
	 * @return
	 */
	public List<Order> findUserOrder(Integer userId, OrderQueryVo orderQueryVo){
		Integer begin = (orderQueryVo.getPage() - 1) * orderQueryVo.getPageSize();
		Integer end = orderQueryVo.getPage() * orderQueryVo.getPageSize();
		orderQueryVo.setBegin(begin);
		orderQueryVo.setEnd(end);
		orderQueryVo.setUserId(userId);
		List<Order> list = orderMapper.findOrderAndOrderDetailListByUser(orderQueryVo);
		for (Order order: list) {
			List<OrderItem> items = order.getOrderItems();
			double price = 0.0;
			for (OrderItem item:items) {
				price += item.getCount() * item.getBook().getNewPrice();
			}
			order.setTotalPrice(price);//计算订单总金额
		}
		return list;
	}

	/**
	 * 查询总页数
	 */
	public Integer findUserOrderPages(Integer userId,OrderQueryVo orderQueryVo){
		orderQueryVo.setUserId(userId);
		int count = orderMapper.findOrderCountByUser(orderQueryVo);
		return (count - 1) / orderQueryVo.getPageSize() + 1;
	}
}
