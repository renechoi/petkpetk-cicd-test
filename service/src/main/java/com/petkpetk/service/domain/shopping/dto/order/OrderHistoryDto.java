package com.petkpetk.service.domain.shopping.dto.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Enumerated;

import com.petkpetk.service.domain.shopping.constant.OrderStatus;
import com.petkpetk.service.domain.shopping.entity.order.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderHistoryDto {

	private Long orderId;

	private OrderStatus orderStatus;

	private List<OrderItemDto> orderItemDtos = new ArrayList<>();

	private LocalDateTime createdAt;

	public OrderHistoryDto(Order order) {
		this.orderId = order.getId();
		this.orderStatus = order.getOrderStatus();
		this.createdAt = order.getCreatedAt();
	}
}
