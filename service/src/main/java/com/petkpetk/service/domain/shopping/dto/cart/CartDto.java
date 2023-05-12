package com.petkpetk.service.domain.shopping.dto.cart;

import java.util.List;

import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.domain.shopping.dto.order.OrderItemDto;
import com.petkpetk.service.domain.shopping.entity.cart.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {


	private Long cartId;

	private Long userId;
	private List<OrderItemDto> orderItemList;
	private Long totalPrice;


	public Cart toCart() {
		return EntityAndDtoConverter.convertToEntity(this, Cart.class);
	}

	public CartDto(Long cartId, Long userId, Long totalPrice) {
		this.cartId = cartId;
		this.userId = userId;
		this.totalPrice = totalPrice;
	}

	public static CartDto of(Long cartId, Long userId, Long totalPrice) {
		return new CartDto(
			cartId,
			userId,
			totalPrice
		);
	}

}
