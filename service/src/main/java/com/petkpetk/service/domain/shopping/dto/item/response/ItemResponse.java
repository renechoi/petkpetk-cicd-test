package com.petkpetk.service.domain.shopping.dto.item.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.domain.shopping.constant.ItemStatus;
import com.petkpetk.service.domain.shopping.dto.item.ItemImageDto;
import com.petkpetk.service.domain.shopping.entity.item.Item;
import com.petkpetk.service.domain.user.dto.UserAccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {
	private Long id;

	private String itemName;
	private Long originalPrice;
	private Double discountRate;
	private Long price;
	private Long itemAmount;
	private String itemDetail;
	private ItemStatus itemStatus;
	private UserAccountDto userAccountDto;
	private Double totalRating;
	private List<ItemImageDto> itemImageDtos = new ArrayList<>();

	public Item toEntity() {
		return Item.of(
			this.itemName,
			this.originalPrice,
			this.discountRate,
			this.price,
			this.itemAmount,
			this.itemDetail,
			this.itemStatus,
			null, // TODO : null 처리
			this.userAccountDto,
			this.totalRating
		);
	}

	public ItemResponse(String itemName,Long originalPrice, Double discountRate, Long price, Long itemAmount, String itemDetail,
		ItemStatus itemStatus, UserAccountDto userAccountDto, Double totalRating) {
		this.itemName = itemName;
		this.originalPrice = originalPrice;
		this.discountRate = discountRate;
		this.price = (long)(originalPrice - originalPrice*discountRate);
		this.itemAmount = itemAmount;
		this.itemDetail = itemDetail;
		this.itemStatus = itemStatus;
		this.userAccountDto = userAccountDto;
		this.totalRating = totalRating;
	}

	public static ItemResponse of(String itemName,Long originalPrice, Double discountRate, Long price, Long itemAmount, String itemDetail,
		ItemStatus itemStatus, UserAccountDto userAccountDto, Double totalRating) {
		return new ItemResponse(itemName,originalPrice, discountRate, (long)(originalPrice - originalPrice*discountRate), itemAmount, itemDetail, itemStatus, userAccountDto, totalRating);
	}

	public static ItemResponse from(Item item) {
		ItemResponse itemResponse = EntityAndDtoConverter.convertToDto(item, ItemResponse.class);
		itemResponse.setItemImageDtos(item.getImages().stream().map(ItemImageDto::from).collect(Collectors.toList()));
		itemResponse.setUserAccountDto(UserAccountDto.from(item.getUserAccount()));
		return itemResponse;
	}

	public static ItemResponse of(Item itemEntity, List<ItemImageDto> itemImageDtos) {
		return new ItemResponse(
			itemEntity.getId(),
			itemEntity.getItemName(),
			itemEntity.getOriginalPrice(),
			itemEntity.getDiscountRate(),
			itemEntity.getPrice(),
			itemEntity.getItemAmount(),
			itemEntity.getItemDetail(),
			itemEntity.getItemStatus(),
			UserAccountDto.from(itemEntity.getUserAccount()),
			itemEntity.getTotalRating(),
			itemImageDtos
		);
	}

}
