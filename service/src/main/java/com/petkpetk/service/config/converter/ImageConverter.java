package com.petkpetk.service.config.converter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.common.PetkpetkImage;
import com.petkpetk.service.config.exception.PetkpetkServerException;

/**
 * Multipart 이미지 파일을 ItemImage, ProfileImage 등 PetkpetkImage를 구현한 Image 객체로 생성하는 Converter.
 * 예시 코드는 아래와 같다.
 * <p>
 * ImageConverter<PetkpetkImage> itemImageConverter = new ImageConverter<>(ItemImage::from);
 * List<PetkpetkImage> convertedImages = itemImageConverter.convertToImages(rawImages);
 * <p>
 * ImageConverter<ProfileImage> profileImageConverter = new ImageConverter<>(ProfileImage::new);
 * List<ProfileImage> convertedImages = profileImageConverter.convertToImages(rawImages);
 * <p>
 * 팩토리 패턴 방식
 * List<ItemImage> images = ImageConverter.of(ItemImage::from).convertToImages(itemDto.getRawImages());
 *
 * @param <T>
 * @author Rene
 */

public class ImageConverter<T extends PetkpetkImage> {

	private final Function<MultipartFile, T> imageConstructor;

	public ImageConverter(Function<MultipartFile, T> imageConstructor) {
		this.imageConstructor = imageConstructor;
	}

	public static <T extends PetkpetkImage> ImageConverter<T> of(Function<MultipartFile, T> imageConstructor) {
		return new ImageConverter<>(imageConstructor);
	}

	public List<T> convertToImages(List<MultipartFile> rawImages) {
		return rawImages.stream().filter(image -> !image.isEmpty()).map(imageConstructor).collect(Collectors.toList());
	}

	public T convertToImage(MultipartFile rawImage) {
		if (rawImage.isEmpty()) {
			throw new PetkpetkServerException();
		}
		return imageConstructor.apply(rawImage);
	}

}
