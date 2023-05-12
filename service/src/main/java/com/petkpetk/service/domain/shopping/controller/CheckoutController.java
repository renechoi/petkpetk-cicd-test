package com.petkpetk.service.domain.shopping.controller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petkpetk.service.domain.shopping.dto.order.request.CheckoutRequest;
import com.petkpetk.service.domain.shopping.dto.order.response.CheckoutResponse;
import com.petkpetk.service.domain.shopping.dto.payment.PaymentRequest;
import com.petkpetk.service.domain.shopping.service.order.OrderService;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;
import com.petkpetk.service.domain.user.exception.UserNotFoundException;
import com.petkpetk.service.domain.user.service.UserAccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {

	private final OrderService orderService;

	@PostMapping()
	public String checkout(@Valid CheckoutRequest checkoutRequest, Model model, @AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal){
		CheckoutResponse checkoutResponse = orderService.createCheckOut(checkoutRequest);
		model.addAttribute("item", checkoutResponse);
		model.addAttribute("payment", new PaymentRequest());
		model.addAttribute("user", userAccountPrincipal);

		return "order/checkout";
	}
}
