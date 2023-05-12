package com.petkpetk.service.domain.shopping.dto.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoReadyResponse {


	private String tid;
	private String next_redirect_mobile_url;
	private String next_redirect_pc_url;
	private String created_at;
}