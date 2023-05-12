package com.petkpetk.service.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petkpetk.service.domain.user.dto.request.UserSignupRequest;
import com.petkpetk.service.domain.user.service.UserAccountService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserAccountController {

	private final UserAccountService userAccountService;

	@GetMapping("/sign-up")
	public String signUp(Model model) {
		model.addAttribute("userAccount", new UserSignupRequest());
		return "user/user/sign-up";
	}
	@GetMapping("/seller/sign-up")
	public String sellerSignUp(Model model) {
		model.addAttribute("userAccount", new UserSignupRequest());
		return "user/seller/sign-up";
	}

	@PostMapping("/sign-up")
	public String signUp(UserSignupRequest userSignupRequest) {
		userAccountService.save(userSignupRequest);
		return "/login";
	}
}
