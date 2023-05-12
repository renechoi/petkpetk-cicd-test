package com.petkpetk.service.domain.shopping.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DeliveryStatus {
	PAYMENT_COMPLETE("결제완료"),
	PREPARING("배송 준비중"),
	CANCEL("취소"),
	DELIVERING("배송중"),
	DELIVERY_COMPLETED("배송완료")
	;

	private final String description;


}
