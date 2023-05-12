package com.petkpetk.service.domain.shopping.dto.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.petkpetk.service.domain.shopping.dto.cart.CartItemDto;
import com.petkpetk.service.domain.shopping.dto.item.ItemDto;
import com.petkpetk.service.domain.shopping.dto.item.ItemImageDto;
import com.petkpetk.service.domain.user.dto.UserAccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDto {
	
	private Long itemId;
	private String itemName;
	private Long originalPrice;
	private Double discountRate;
	private Long price;
	private String itemDetail;
	private UserAccountDto userAccountDto;
	private List<ItemImageDto> itemImageDtos = new ArrayList<>();
	private Long orderCount = 0L;



	public void update(ItemDto itemDto) {
		this.itemName =   itemDto.getItemName();
		this.originalPrice = itemDto.getOriginalPrice();
		this.discountRate = itemDto.getDiscountRate();
		this.price = itemDto.getPrice();
		this.itemDetail = itemDto.getItemDetail();
		this.userAccountDto = itemDto.getUserAccountDto();
		this.itemImageDtos = itemDto.getItemImageDtos();
	}

	public static CheckoutDto of(Long id) {
		return new CheckoutDto(id);
	}

	public static CheckoutDto of(Long id, Long cartItemCount) {
		return new CheckoutDto(id, cartItemCount);
	}

	private CheckoutDto(Long itemId) {
		this.itemId = itemId;
	}

	private CheckoutDto(Long itemId, Long orderCount) {
		this.itemId = itemId;
		this.orderCount = orderCount;
	}
}
