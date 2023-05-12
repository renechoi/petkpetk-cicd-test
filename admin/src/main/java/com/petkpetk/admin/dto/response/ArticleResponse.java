package com.petkpetk.admin.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.dto.ArticleDto;
import com.petkpetk.admin.dto.ItemDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private Long id;
    private String name;
    private String email;
    private String title;
    private String content;
    private Long hit;
    private String categoryType;
    private List<String> hashtags;
    private LocalDateTime createdAt;

    public static ArticleResponse from(ArticleDto dto) {
        return EntityAndDtoConverter.convertToDto(dto, ArticleResponse.class);
    }
}
