package com.petkpetk.service.domain.shopping.dto.order;

import com.petkpetk.service.domain.shopping.constant.OrderStatus;
import com.petkpetk.service.domain.shopping.entity.order.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

	private Long Id;
	private String itemName;
	private Long orderCount;
	private Long orderPrice;
	private Long orderId;
	private String imageUrl;
	private Long userId;
	private Long productId;
	private OrderStatus orderStatus;

	public OrderItemDto(OrderItem orderItem, String imageUrl){
		this.itemName = orderItem.getItem().getItemName();
		this.orderCount = orderItem.getOrderCount();
		this.orderPrice = orderItem.getOrderPrice();
		this.imageUrl = imageUrl;
	}
}
