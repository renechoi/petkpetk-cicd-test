package com.petkpetk.service.domain.shopping.dto.order.response;

import java.util.List;

import com.petkpetk.service.domain.shopping.dto.order.CheckoutDto;
import com.petkpetk.service.domain.shopping.dto.priceInfo.CheckoutPriceInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutResponse {

	private List<CheckoutDto> checkoutDtos;
	private CheckoutPriceInfo checkoutPriceInfo;

	public CheckoutResponse(List<CheckoutDto> checkoutDtos) {
		this.checkoutDtos = checkoutDtos;
	}

	public static CheckoutResponse of(List<CheckoutDto> checkoutDtos) {
		return new CheckoutResponse(checkoutDtos);
	}

	public static CheckoutResponse of(List<CheckoutDto> checkoutDtos, CheckoutPriceInfo checkoutPriceInfo) {
		return new CheckoutResponse(checkoutDtos, checkoutPriceInfo);
	}
}
