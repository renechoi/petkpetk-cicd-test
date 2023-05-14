package com.petkpetk.service.common.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.dto.request.UserAskRequest;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;
import com.petkpetk.service.domain.user.service.UserAskService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("about/")
public class AboutController {

	private final UserAskService userAskService;

	@GetMapping("notice")
	public String noticeView() {
		return "about/notice";
	}

	@GetMapping("ask")
	public String askView(Model model, @AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal) {
		model.addAttribute("ask",
			userAccountPrincipal == null ? null : userAskService.getUserAsks(userAccountPrincipal));
		model.addAttribute("askRequest", new UserAskRequest());
		return "about/ask";
	}

	@PostMapping("askPost")
	public String askPost(UserAskRequest userAskRequest,
		@AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal) {
		userAskService.saveAsk(userAskRequest, UserAccountDto.from(userAccountPrincipal));
		return "redirect:/about/ask";
	}

	@PostMapping("ask/delete/{askId}")
	public String askDelete(@PathVariable("askId") Long askId) {
		userAskService.deleteAsk(askId);
		return "redirect:/about/ask";
	}

}
