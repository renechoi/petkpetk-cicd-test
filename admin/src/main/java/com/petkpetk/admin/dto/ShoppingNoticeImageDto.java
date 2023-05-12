package com.petkpetk.admin.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ShoppingNoticeImageDto {
	@JsonProperty("id")
	private String id;

	@JsonProperty("uniqueName")
	private String uniqueName;

	@JsonProperty("originalName")
	private String originalName;

	@JsonProperty("imageUrl")
	private String imageUrl;

	private List<MultipartFile> images;
}