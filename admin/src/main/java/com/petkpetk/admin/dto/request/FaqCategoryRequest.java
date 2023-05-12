package com.petkpetk.admin.dto.request;

import com.petkpetk.admin.dto.FaqCategoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaqCategoryRequest {

	private String name;
	private String description;
}
