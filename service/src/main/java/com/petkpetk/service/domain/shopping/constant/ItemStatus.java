package com.petkpetk.service.domain.shopping.constant;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ItemStatus {
	SELL("판매중"),
	SOLD_OUT("판매종료")

	;


	private final String description;
}
