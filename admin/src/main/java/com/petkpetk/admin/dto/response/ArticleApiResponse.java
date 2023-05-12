package com.petkpetk.admin.dto.response;

import java.util.Collections;
import java.util.List;

import com.petkpetk.admin.dto.ArticleDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleApiResponse {
    private List<ArticleDto> article;
    private Long totalCount;

    public static ArticleApiResponse empty() {
        return new ArticleApiResponse(Collections.emptyList(), 0L);
    }

}
