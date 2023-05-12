package com.petkpetk.admin.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.admin.dto.FaqCategoryDto;
import com.petkpetk.admin.entity.FaqCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaqRequest {

	private Long id;
	private String title;
	private String content;
	private FaqCategoryDto faqCategory;
}
