package com.shop.shop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.shop.entity.Cart;
import com.shop.shop.entity.CartVo;
import com.shop.shop.entity.UserCartVo;
import com.shop.shop.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CartService extends ServiceImpl<CartMapper, Cart> {

	@Autowired
	private CartMapper cartMapper;

	/**
	 * 根据用户查找购物车记录
	 * @param userId
	 * @return
	 */
	public List<CartVo> findCartByUser(int userId){
		return cartMapper.findCartListByUserId(userId);
	}

	/**
	 * 统计当前用户购物车的总计
	 * @param list
	 * @return
	 */
	public double getCartItemTotal(List<CartVo> list){
		double sum = 0.0;
		for(CartVo cart: list){
			sum += cart.getCount() * cart.getNewPrice();
		}
		return sum;
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public String batchDelete(String ids){
		if (ids != null){
			String[] idArray = ids.split(",");
			cartMapper.deleteBatchIds(Arrays.asList(idArray));
		}
		return "success";
	}

	/**
	 * 包装用户购物车信息数据
	 * @param list
	 * @return
	 */
	public UserCartVo wrapperCart(List<CartVo> list){

		//将用户的购物车信息存放到session中
		UserCartVo userCartVo = new UserCartVo();
		userCartVo.setNum(list.size());
		userCartVo.setTotalPrice(getCartItemTotal(list));
		return userCartVo;
	}
	/**
	 * 根据购物车id查询对应的记录
	 * @param ids
	 * @return
	 */
	public List<CartVo> findCartByIds(String ids){
		return cartMapper.findCartListByIds(Arrays.asList(ids));
	}
}
