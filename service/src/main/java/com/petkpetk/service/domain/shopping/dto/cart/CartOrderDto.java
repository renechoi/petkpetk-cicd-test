package com.petkpetk.service.domain.shopping.dto.cart;

import java.util.List;

import lombok.Data;

@Data
public class CartOrderDto {

	private Long cartItemId;

	private List<CartOrderDto> cartOrderDtos;
}
