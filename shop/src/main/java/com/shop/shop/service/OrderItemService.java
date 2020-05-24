package com.shop.shop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.shop.entity.OrderItem;
import com.shop.shop.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService extends ServiceImpl<OrderItemMapper, OrderItem> {
}
