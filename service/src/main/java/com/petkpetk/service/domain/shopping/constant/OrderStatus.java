package com.petkpetk.service.domain.shopping.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {
	ORDER("주문상태"),
	CANCEL("주문취소상태"),
	COMPLETE("주문완료상태")
	;

	private final String description;
}
