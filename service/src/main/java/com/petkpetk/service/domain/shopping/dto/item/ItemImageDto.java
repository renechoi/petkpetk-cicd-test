package com.petkpetk.service.domain.shopping.dto.item;

import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.domain.shopping.entity.item.ItemImage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemImageDto {

	private Long id;

	private  String uniqueName;

	private String originalName;

	private String imageUrl;

	private String representativeImageYn;

	public ItemImageDto(String uniqueName, String originalName,String imageUrl, String representativeImageYn) {
		this.uniqueName = uniqueName;
		this.originalName = originalName;
		this.imageUrl = imageUrl;
		this.representativeImageYn = representativeImageYn;
	}

	public ItemImageDto(String originalName) {
		this.originalName = originalName;
	}

	public ItemImageDto(String originalName, String uniqueName) {
		this.originalName = originalName;
		this.uniqueName = uniqueName;
	}

	public ItemImageDto(String originalName, String uniqueName, String representativeImageYn) {
		this.originalName = originalName;
		this.uniqueName = uniqueName;
		this.representativeImageYn = representativeImageYn;
	}

	public static ItemImageDto of(ItemImage itemImage){
		return ItemImageDto.of(itemImage.getUniqueName(), itemImage.getOriginalName(), itemImage.getImageUrl(), itemImage.getRepresentativeImageYn());
	}

	public static ItemImageDto of(String imageName, String originalImageName, String imageUrl, String representativeImageYn){
		return new ItemImageDto(imageName,originalImageName,imageUrl,representativeImageYn);
	}

	public static ItemImageDto from(ItemImage itemImage) {
		return EntityAndDtoConverter.convertToDto(itemImage, ItemImageDto.class);
	}

	public static ItemImageDto of(String originalName) {
		return new ItemImageDto(originalName);
	}

	public static ItemImageDto of(String originalName, String uniqueName) {
		return new ItemImageDto(originalName, uniqueName);
	}

	public static ItemImageDto of(String originalName, String uniqueName, String representativeImageYn) {
		return new ItemImageDto(originalName, uniqueName, representativeImageYn);
	}
}
