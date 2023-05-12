package com.petkpetk.admin.config.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

	ADMIN("ROLE_ADMIN", "관리자"),
	STAFF("ROLE_STAFF", "스태프");


	private final String roleName;
	private final String description;
}
