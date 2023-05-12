package com.petkpetk.service.domain.community.constatnt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryType {

	DOG("dog", "강아지"),
	CAT("cat", "고양이"),
	LITTLE_ANIMALS("littleAnimals", "소동물"),
	OTHERS("others", "기타");

	private final String categoryName;
	private final String description;
}
