package com.shop.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shop.shop.entity.Cart;
import com.shop.shop.entity.CartVo;
import com.shop.shop.entity.User;
import com.shop.shop.entity.UserCartVo;
import com.shop.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.xml.bind.PrintConversionEvent;
import java.util.List;


/**
 * 购物车控制器
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	/**
	 * 添加记录
	 * @param cart
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add")
	public String add(Cart cart, HttpSession session){
		User user = (User)session.getAttribute("user");
		cart.setUserId(user.getId());
		//判断是否已经在购物车中存在该记录
		QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
		cartQueryWrapper.eq("user_id",user.getId());
		cartQueryWrapper.eq("book_id",cart.getBookId());
		Cart queryCart = cartService.getOne(cartQueryWrapper);
		if(queryCart == null){
			cartService.save(cart);
		}
		else{
			queryCart.setCount(queryCart.getCount() + cart.getCount());
			cartService.updateById(queryCart);
		}
		return "success";
	}

	/**
	 * 查询当前用户购物车列表
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpSession session, Model model){
		User user = (User)session.getAttribute("user");
		List<CartVo> cartVos = cartService.findCartByUser(user.getId());

		UserCartVo userCartVo = cartService.wrapperCart(cartVos);
		session.setAttribute("userCartInfo",userCartVo);

		model.addAttribute("cartList",cartVos);
		return "cart";
	}

	/**
	 * 更新购物车信息
	 * @param session
	 * @param cart
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public String update(HttpSession session, Cart cart){
		cartService.updateById(cart);
		User user = (User)session.getAttribute("user");
		List<CartVo> cartVos = cartService.findCartByUser(user.getId());

		UserCartVo userCartVo = cartService.wrapperCart(cartVos);
		session.setAttribute("userCartInfo",userCartVo);

		double price = cartService.getCartItemTotal(cartVos);
		return String.valueOf(price);
	}

	/**
	 * 删除购物车
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(String ids){
		return cartService.batchDelete(ids);

	}


}
