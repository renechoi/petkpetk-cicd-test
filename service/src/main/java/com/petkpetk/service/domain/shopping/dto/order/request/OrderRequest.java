package com.petkpetk.service.domain.shopping.dto.order.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {


	@NotNull(message = "상품 아이디는 필수 입력 값 입니다")
	private Long itemId;
	@Min(value = 1, message = "최소 주문 수량은 1개 입니다")
	@Max(value = 999, message = "최대 주문 수량은 999개 입니다")
	private Long orderCount;

}
