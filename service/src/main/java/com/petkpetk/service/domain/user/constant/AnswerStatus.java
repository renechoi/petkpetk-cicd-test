package com.petkpetk.service.domain.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AnswerStatus {
	ANSWERED("답변 완료"),
	ANSWERING("답변 전");

	private final String description;
}
