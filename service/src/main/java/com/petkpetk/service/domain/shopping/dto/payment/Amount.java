package com.petkpetk.service.domain.shopping.dto.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * KAKAO PAY 결제 금액 정보
 */
@Getter
@Setter
@ToString
public class Amount {

	private int total;
	private int tax_free;
	private int tax;
	private int point;
	private int discount;
}