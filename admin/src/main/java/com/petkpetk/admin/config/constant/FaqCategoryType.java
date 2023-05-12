package com.petkpetk.admin.config.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FaqCategoryType {

	USAGE_RELATED_INQUIRIES("USAGE_RELATED_INQUIRIES", "이용문의"),
	STAFF("ROLE_STAFF", "스태프");


	private final String CategoryName;
	private final String description;
}
