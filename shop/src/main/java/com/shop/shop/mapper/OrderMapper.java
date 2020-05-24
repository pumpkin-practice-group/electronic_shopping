package com.shop.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.shop.entity.Order;
import com.shop.shop.entity.OrderQueryVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper extends BaseMapper<Order> {

	//根据用户id查询用户订单以及订单明细
	List<Order> findOrderAndOrderDetailListByUser(OrderQueryVo orderQueryVo);

	Integer findOrderCountByUser(OrderQueryVo orderQueryVo);
}
