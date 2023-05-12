package com.petkpetk.service.domain.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QnACategory {
	INQUIRY("이용문의"),
	PAYMENT("결재/영수증");

	private final String description;
}
