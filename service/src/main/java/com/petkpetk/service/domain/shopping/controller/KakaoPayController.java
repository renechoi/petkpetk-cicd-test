package com.petkpetk.service.domain.shopping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petkpetk.service.domain.shopping.dto.payment.KakaoApproveResponse;
import com.petkpetk.service.domain.shopping.dto.payment.KakaoCancelResponse;
import com.petkpetk.service.domain.shopping.dto.payment.PaymentRequest;
import com.petkpetk.service.domain.shopping.service.payment.KakaoPayService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaoPayController {

	private final KakaoPayService kakaoPayService;

	@PostMapping("/ready")
	public String readyToKakaoPay(PaymentRequest paymentRequest) {
		 return "redirect:" + kakaoPayService.kakaoPayReady(paymentRequest).getNext_redirect_pc_url();
	}

	/**
	 * {"aid":"A45cc63a34994612eb92","tid":"T45cc62b306d7b2d125c","cid":"TC0ONETIME","sid":null,"partner_order_id":"가맹점 주문 번호","partner_user_id":"가맹점 회원 ID","payment_method_type":"MONEY",
	 * "amount":{"total":34000,"tax_free":10,"tax":0,"point":0,"discount":0},
	 * "item_name":"item1item2item3","item_code":null,"quantity":1,"created_at":"2023-05-11T19:40:43","approved_at":"2023-05-11T19:41:01","payload":null}
	 * @param pgToken
	 * @return
	 */
	@GetMapping("/success")
	public String afterPayRequest(@RequestParam("pg_token") String pgToken, Model model) {
		KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken);
		model.addAttribute("kakao", kakaoApprove);
		return "order/kakao-pay/success";
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "order/kakao-pay/cancel";
	}

	@GetMapping("/fail")
	public String fail() {
		return "order/kakao-pay/fail";
	}

	@PostMapping("/refund")
	public ResponseEntity refund() {
		KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel();
		return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);
	}
}
