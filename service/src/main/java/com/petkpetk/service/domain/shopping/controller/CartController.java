package com.petkpetk.service.domain.shopping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petkpetk.service.domain.shopping.dto.cart.request.CartItemRegisterRequest;
import com.petkpetk.service.domain.shopping.dto.cart.request.CartItemUpdateRequest;
import com.petkpetk.service.domain.shopping.dto.cart.response.CartItemResponse;
import com.petkpetk.service.domain.shopping.dto.order.request.CheckoutRequest;
import com.petkpetk.service.domain.shopping.exception.OutOfStockException;
import com.petkpetk.service.domain.shopping.exception.StockAlreadyInCartException;
import com.petkpetk.service.domain.shopping.service.cart.CartService;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

	private final CartService cartService;

	@GetMapping("")
	public String cartItems(@AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal, Model model) {
		CartItemResponse cartItemResponse = cartService.getCartItems(userAccountPrincipal.getEmail());
		model.addAttribute("order", new CheckoutRequest(cartItemResponse));
		model.addAttribute("cartItems", cartItemResponse);
		return "cart/cart";
	}

	@ResponseBody
	@PostMapping(value = "")
	public ResponseEntity<?> cartItems(@RequestBody CartItemRegisterRequest cartItemRegisterRequest,
		@AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal) {
		if (userAccountPrincipal == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		try {
			cartService.addCartItems(cartItemRegisterRequest, userAccountPrincipal.getEmail());
			return ResponseEntity.ok().body(true);
		} catch (OutOfStockException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (StockAlreadyInCartException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("잠시 후 다시 시도해주세요.");
		}
	}

	@ResponseBody
	@PostMapping("/update")
	public ResponseEntity<?> updateCart(@RequestBody CartItemUpdateRequest cartItemUpdateRequest,
		@AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal) {

		try {
			cartService.updateCart(cartItemUpdateRequest, userAccountPrincipal.getEmail());
		} catch (OutOfStockException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
}
