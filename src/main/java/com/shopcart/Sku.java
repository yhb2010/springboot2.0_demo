package com.shopcart;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//库存信息：库存表保存的商品id,颜色,尺码,库存等信息
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Sku implements Serializable {

	private Long id;
	//单价
	private Float price;
	//颜色
	private String color;
	//商品信息
	private String product;
	//库存
	private Integer stock;

	public Sku(Long id, Float price) {
		super();
		this.id = id;
		this.price = price;
	}

}
