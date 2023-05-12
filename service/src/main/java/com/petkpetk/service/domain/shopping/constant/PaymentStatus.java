package com.petkpetk.service.domain.shopping.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentStatus {
	PAYMENT_WAIT("결제 대기중"),
	PAID("결제완료"),
	CANCELED("결제취소")
	;

	private final String description;
}
