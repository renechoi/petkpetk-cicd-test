package com.petkpetk.service.domain.shopping.dto.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 결제 승인 요청 시 사용
 */
@Getter
@Setter
@ToString
public class KakaoApproveResponse {

	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partner_order_id;
	private String partner_user_id;
	private String payment_method_type;
	private Amount amount;
	private String item_name;
	private String item_code;
	private int quantity;
	private String created_at;
	private String approved_at;
	private String payload;
}