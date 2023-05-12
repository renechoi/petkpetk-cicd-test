package com.petkpetk.admin.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.admin.dto.ShoppingNoticeImageDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingNoticeRegisterRequest {

	private Long id;
	private String title;
	private String content;
	private List<MultipartFile> images;
}
