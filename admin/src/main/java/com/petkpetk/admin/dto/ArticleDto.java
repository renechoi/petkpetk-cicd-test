package com.petkpetk.admin.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String name;
    private String email;
    private String title;
    private String content;
    private Long hit;
    private String categoryType;
    private List<String> hashtags;
    private LocalDateTime createdAt;
}
