package com.petkpetk.service.domain.shopping.dto.priceInfo;

import com.petkpetk.service.domain.shopping.dto.order.request.CheckoutRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutPriceInfo {
	private Long totalPrice;
	private Long deliveryPrice;
	private Long couponDiscount;
	private Long pointDiscount;
	private Long finalPaymentPrice;

	public static CheckoutPriceInfo of(CheckoutRequest checkoutRequest) {
		ItemPriceInfo itemPriceInfo = checkoutRequest.getItemPriceInfo();

		return new CheckoutPriceInfo(
			itemPriceInfo.getTotalPrice(),
			itemPriceInfo.getDeliveryPrice(),
			itemPriceInfo.getCouponDiscount(),
			itemPriceInfo.getPointDiscount(),
			calculateFinalPaymentPrice(itemPriceInfo.getTotalPrice(), itemPriceInfo.getDeliveryPrice(), itemPriceInfo.getCouponDiscount(),
				itemPriceInfo.getPointDiscount())
		);
	}

	private static Long calculateFinalPaymentPrice(Long totalPrice, Long deliveryPrice, Long couponDiscount,
		Long pointDiscount) {
		return totalPrice + deliveryPrice - couponDiscount - pointDiscount;
	}
}
