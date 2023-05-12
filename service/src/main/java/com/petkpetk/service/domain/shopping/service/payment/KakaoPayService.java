package com.petkpetk.service.domain.shopping.service.payment;

import javax.transaction.Transactional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.petkpetk.service.config.properties.LocalProperty;
import com.petkpetk.service.domain.shopping.dto.payment.KakaoApproveResponse;
import com.petkpetk.service.domain.shopping.dto.payment.KakaoCancelResponse;
import com.petkpetk.service.domain.shopping.dto.payment.KakaoReadyResponse;
import com.petkpetk.service.domain.shopping.dto.payment.PaymentRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {

	static final String cid = LocalProperty.getInstance().getKakaoPaymentCid();
	static final String admin_Key = LocalProperty.getInstance().getKakaoPaymentAdminKey();
	private KakaoReadyResponse kakaoReady;
	private final RestTemplate restTemplate;

	public KakaoReadyResponse kakaoPayReady(PaymentRequest paymentRequest) {

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("partner_order_id", "페크페크 가입 사업자");
		parameters.add("partner_user_id", "가맹점 회원 ID");
		parameters.add("item_name", paymentRequest.getItemName());
		parameters.add("quantity", "1");
		parameters.add("total_amount", paymentRequest.getFinalPaymentPrice());
		parameters.add("tax_free_amount", "10");
		parameters.add("approval_url", LocalProperty.getInstance().getServerPort() +"/payment/success");
		parameters.add("cancel_url", LocalProperty.getInstance().getServerPort() +"/payment/cancel");
		parameters.add("fail_url", LocalProperty.getInstance().getServerPort() + "/payment/fail");

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

		kakaoReady = restTemplate.postForObject(
			"https://kapi.kakao.com/v1/payment/ready", requestEntity, KakaoReadyResponse.class);

		return kakaoReady;

	}

	public KakaoApproveResponse approveResponse(String pgToken) {

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("tid", kakaoReady.getTid());
		parameters.add("partner_order_id", "페크페크 가입 사업자");
		parameters.add("partner_user_id", "가맹점 회원 ID");
		parameters.add("pg_token", pgToken);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.postForObject(
			"https://kapi.kakao.com/v1/payment/approve",
			requestEntity,
			KakaoApproveResponse.class);
	}

	public KakaoCancelResponse kakaoCancel() {

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		parameters.add("cid", cid);
		parameters.add("tid", "환불할 결제 고유 번호");
		parameters.add("cancel_amount", "환불 금액");
		parameters.add("cancel_tax_free_amount", "환불 비과세 금액");

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.postForObject(
			"https://kapi.kakao.com/v1/payment/cancel",
			requestEntity,
			KakaoCancelResponse.class);
	}

	private HttpHeaders getHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();

		String auth = "KakaoAK " + admin_Key;

		httpHeaders.set("Authorization", auth);
		httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		return httpHeaders;
	}
}