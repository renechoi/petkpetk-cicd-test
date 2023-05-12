package com.petkpetk.service.domain.shopping.dto.cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemUpdateRequest {

	private Long itemId;
	private Long cartItemCount;
}
