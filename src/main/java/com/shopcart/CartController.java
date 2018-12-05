package com.shopcart;

import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	//http://127.0.0.10:8083/shopping/buyerCart?skuId=1&amount=10
	// 加入购物车
	@RequestMapping(value = "/shopping/buyerCart")
	public <T> String buyerCart(Long skuId, Integer amount, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 将对象转换成json字符串/json字符串转成对象
		ObjectMapper om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
		BuyerCart buyerCart = null;
		// 1,获取Cookie中的购物车
		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				//
				if (Constants.BUYER_CART.equals(cookie.getName())) {
					// 购物车 对象 与json字符串互转
					buyerCart = om.readValue(URLDecoder.decode(cookie.getValue(),"utf-8"), BuyerCart.class);
					break;
				}
			}
		}

		// 2,Cookie中没有购物车, 创建购物车对象
		if (null == buyerCart) {
			buyerCart = new BuyerCart();
		}

		// 3, 将当前款商品追加到购物车
		if (null != skuId && null != amount) {
			Sku sku = new Sku();
			sku.setId(skuId);
			BuyerItem buyerItem = new BuyerItem();
			buyerItem.setSku(sku);
			// 设置数量
			buyerItem.setAmount(amount);
			// 添加购物项到购物车
			buyerCart.addItem(buyerItem);
		}

		// 排序 倒序
		List<BuyerItem> items = buyerCart.getItems();
		Collections.sort(items, new Comparator<BuyerItem>() {

			@Override
			public int compare(BuyerItem o1, BuyerItem o2) {
				return -1;
			}

		});

		// 前三点 登录和非登录做的是一样的操作, 在第四点需要判断
		// 判断用户是否登录
		String username = RequestUtils.getAttributterForUsername(RequestUtils.getCSessionId(request, response));
		if (null != username) {
			// 登录了
			// 4, 将购物车追加到Redis中
			cartService.insertBuyerCartToRedis(buyerCart, username);
			// 5, 清空Cookie 设置存活时间为0, 立马销毁
			Cookie cookie = new Cookie(Constants.BUYER_CART, null);
			cookie.setPath("/");
			cookie.setMaxAge(-0);
			response.addCookie(cookie);
		} else {
			// 未登录
			// 4, 保存购物车到Cookie中
			// 将对象转换成json格式
			Writer w = new StringWriter();
			om.writeValue(w, buyerCart);
			Cookie cookie = new Cookie(Constants.BUYER_CART, URLEncoder.encode(w.toString(), "utf-8"));
			// 设置path是可以共享cookie
			cookie.setPath("/");
			// 设置Cookie过期时间: -1 表示关闭浏览器失效 0: 立即失效 >0: 单位是秒, 多少秒后失效
			cookie.setMaxAge(24 * 60 * 60);
			// 5,Cookie写会浏览器
			response.addCookie(cookie);
		}

		// 6, 重定向
		return "redirect:/shopping/toCart";
	}

	// 去购物车结算, 这里有两个地方可以直达: 1,在商品详情页 中点击加入购物车按钮 2, 直接点击购物车按钮
	@RequestMapping(value = "/shopping/toCart")
	public String toCart(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 将对象转换成json字符串/json字符串转成对象
		ObjectMapper om = new ObjectMapper();
		om.setSerializationInclusion(Include.NON_NULL);
		BuyerCart buyerCart = null;
		// 1,获取Cookie中的购物车
		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				//
				if (Constants.BUYER_CART.equals(cookie.getName())) {
					// 购物车 对象 与json字符串互转
					buyerCart = om.readValue(URLDecoder.decode(cookie.getValue(), "utf-8"), BuyerCart.class);
					break;
				}
			}
		}

		// 判断是否登录
		String username = RequestUtils.getAttributterForUsername(RequestUtils.getCSessionId(request, response));
		if (null != username) {
			// 登录了
			// 2, 购物车 有东西, 则将购物车的东西保存到Redis中
			if (buyerCart != null) {
				cartService.insertBuyerCartToRedis(buyerCart, username);
				// 清空Cookie 设置存活时间为0, 立马销毁
				Cookie cookie = new Cookie(Constants.BUYER_CART, null);
				cookie.setPath("/");
				cookie.setMaxAge(-0);
				response.addCookie(cookie);
			}
			// 3, 取出Redis中的购物车
			buyerCart = cartService.selectBuyerCartFromRedis(username);
		}

		// 4, 没有 则创建购物车
		if (null == buyerCart) {
			buyerCart = new BuyerCart();
		}

		// 5, 将购物车装满, 前面只是将skuId装进购物车, 这里还需要查出sku详情
		List<BuyerItem> items = buyerCart.getItems();
		if (items.size() > 0) {
			// 只有购物车中有购物项, 才可以将sku相关信息加入到购物项中
			for (BuyerItem buyerItem : items) {
				buyerItem.setSku(cartService.selectSkuById(buyerItem.getSku().getId()));
			}
		}

		// 5,上面已经将购物车装满了, 这里直接回显页面
		model.addAttribute("buyerCart", buyerCart);

		// 跳转购物页面
		return "cart";
	}

	//去结算
	@RequestMapping(value="/buyer/trueBuy")
	public String trueBuy(String[] skuIds, Model model, HttpServletRequest request, HttpServletResponse response){
	    //1, 购物车必须有商品,
	    //取出用户名  再取出购物车
	    String username = RequestUtils.getAttributterForUsername(RequestUtils.getCSessionId(request, response));
	    //取出所有购物车
	    BuyerCart buyerCart = cartService.selectBuyerCartFromRedisBySkuIds(skuIds, username);
	    List<BuyerItem> items = buyerCart.getItems();
	     if (items.size() > 0) {
	         //购物车中有商品
	         //判断所勾选的商品是否都有货, 如果有一件无货, 那么就刷新页面.
	         Boolean flag = true;
	         //2, 购物车中商品必须有库存 且购买大于库存数量时视为无货. 提示: 购物车原页面不动. 有货改为无货, 加红提醒.
	         for (BuyerItem buyerItem : items) {
	             //装满购物车的购物项, 当前购物项只有skuId这一个东西, 我们还需要购物项的数量去判断是否有货
	             buyerItem.setSku(cartService.selectSkuById(buyerItem.getSku().getId()));
	             //校验库存
	             if (buyerItem.getAmount() > buyerItem.getSku().getStock()) {
	                 //无货
	                 buyerItem.setIsHave(false);
	                 flag = false;
	             }
	             if (!flag) {
	                 //无货, 原页面不动, 有货改成无货, 刷新页面.
	                 model.addAttribute("buyerCart", buyerCart);
	                 return "cart";
	             }
	         }
	     }else {
	         //购物车没有商品
	         //没有商品: 1>原购物车页面刷新(购物车页面提示没有商品)
	         return "redirect:/shopping/toCart";
	     }


	     //3, 正常进入下一个页面
	     return "order";
	 }

}
