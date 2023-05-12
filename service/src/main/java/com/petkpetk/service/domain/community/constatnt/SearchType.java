package com.petkpetk.service.domain.community.constatnt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchType {
    TITLE("제목"),
    CONTENT("본문"),
    NICKNAME("닉네임"),
    HASHTAG("해시태그"),
    CATEGORY("카테고리");

   private final String description;
}
