package com.petkpetk.service.domain.shopping.dto.review;

import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.domain.shopping.entity.review.ReviewImage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewImageDto {

	private Long id;
	private  String uniqueName;
	private  String originalName;
	private String imageUrl;

	public ReviewImageDto(String uniqueName, String originalName, String imageUrl) {
		this.uniqueName = uniqueName;
		this.originalName = originalName;
		this.imageUrl = imageUrl;
	}

	public ReviewImageDto(String originalName) {
		this.originalName = originalName;
	}

	public ReviewImageDto( String originalName, String uniqueName) {
		this.uniqueName = uniqueName;
		this.originalName = originalName;
	}

	public static ReviewImageDto of(ReviewImage reviewImage) {
		return ReviewImageDto.of(reviewImage.getUniqueName(), reviewImage.getOriginalName(), reviewImage.getImageUrl());
	}

	public static ReviewImageDto of(String uniqueName, String originalName, String imageUrl) {
		return new ReviewImageDto(uniqueName, originalName, imageUrl);
	}

	public static ReviewImageDto from(ReviewImage reviewImage) {
		return EntityAndDtoConverter.convertToDto(reviewImage, ReviewImageDto.class);
	}

	public static ReviewImageDto of(String originalName) {
		return new ReviewImageDto(originalName);
	}

	public static ReviewImageDto of( String originalName, String uniqueName) {
		return new ReviewImageDto(originalName, uniqueName);
	}
}







