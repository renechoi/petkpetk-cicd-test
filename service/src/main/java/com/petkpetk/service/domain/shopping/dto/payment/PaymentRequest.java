package com.petkpetk.service.domain.shopping.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

	private String itemName;
	private String finalPaymentPrice;

}
