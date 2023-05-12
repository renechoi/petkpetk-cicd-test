package com.petkpetk.service.domain.shopping.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

	private Long payId;
	private Long orderId;

	private Long userId;

	private Long productId;
	private Long totalCost;
	private String payToken;
	private String payApp;
}
