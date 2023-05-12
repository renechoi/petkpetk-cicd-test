package com.petkpetk.service.domain.shopping.dto.priceInfo;

import java.util.Set;

import com.petkpetk.service.domain.shopping.dto.cart.CartItemDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPriceInfo {
	private Long totalPrice = 0L;
	private Long deliveryPrice = 0L;
	private Long itemTotalDiscount = 0L;
	private Long couponDiscount = 0L;
	private Long pointDiscount = 0L;
	private Long paymentPrice = 0L;

	public static ItemPriceInfo of(Set<CartItemDto> cartItemDtos, Long deliveryPrice,
		Long itemTotalDiscount, Long couponDiscount, Long pointDiscount) {

		Long totalPrice = calculateTotalPrice(cartItemDtos);
		Long paymentPrice = calculatePaymentPrice(totalPrice, deliveryPrice, itemTotalDiscount);

		return new ItemPriceInfo(totalPrice, deliveryPrice, itemTotalDiscount, paymentPrice, couponDiscount,
			pointDiscount);
	}

	private static Long calculateTotalPrice(Set<CartItemDto> cartItemDtos) {
		return cartItemDtos.stream()
			.mapToLong(CartItemDto::getSumPrice)
			.sum();
	}

	private static Long calculatePaymentPrice(Long totalPrice, Long deliveryPrice, Long totalDiscountPrice) {
		return totalPrice + deliveryPrice - totalDiscountPrice;
	}

}
