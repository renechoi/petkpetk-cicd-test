package com.petkpetk.service.domain.shopping.dto.order.response;

import java.util.List;

import com.petkpetk.service.domain.shopping.dto.item.ItemDto;
import com.petkpetk.service.domain.shopping.dto.order.OrderItemDto;

import lombok.Data;

@Data
public class OrderCheckOutResponse {

	private List<OrderItemDto> orderItemDtos;
	private Long orderCount;
}
