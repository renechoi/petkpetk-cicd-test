package com.petkpetk.service.domain.shopping.dto.item.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.domain.shopping.constant.ItemStatus;
import com.petkpetk.service.domain.shopping.dto.item.ItemImageDto;
import com.petkpetk.service.domain.shopping.entity.item.Item;
import com.petkpetk.service.domain.shopping.entity.item.ItemImage;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.entity.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRegisterRequest {
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

	private List<MultipartFile> images = new ArrayList<>();

	private List<ItemImageDto> itemImageDtos = new ArrayList<>();


	public Item toEntity(List<ItemImage> images) {
		return Item.of(
			this.itemName,
			this.originalPrice,
			this.discountRate,
			(long)(this.originalPrice - this.originalPrice*this.discountRate),
			this.itemAmount,
			this.itemDetail,
			this.itemStatus,
			images,
			this.userAccountDto,
			this.totalRating
		);
	}

	public ItemRegisterRequest(String itemName,Long originalPrice, Double discountRate, Long price, Long itemAmount, String itemDetail,
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

	public static ItemRegisterRequest of(String itemName, Long originalPrice, Double discountRate, Long price, Long itemAmount, String itemDetail,
		ItemStatus itemStatus, UserAccountDto userAccount, Double totalRating) {
		return new ItemRegisterRequest(itemName, originalPrice, discountRate, (long)(originalPrice - originalPrice*discountRate), itemAmount, itemDetail, itemStatus, userAccount, totalRating);
	}

	public static ItemRegisterRequest of(Item item) {
		return ItemRegisterRequest.of(item.getItemName(),item.getOriginalPrice(), item.getDiscountRate(), item.getPrice(), item.getItemAmount(),
			item.getItemDetail(), item.getItemStatus(), UserAccountDto.fromEntity(item.getUserAccount()), item.getTotalRating());
	}
}
