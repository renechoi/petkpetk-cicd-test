package com.petkpetk.service.common.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petkpetk.service.domain.shopping.dto.item.ItemSearchDto;
import com.petkpetk.service.domain.shopping.dto.item.ManageItemDto;
import com.petkpetk.service.domain.shopping.service.item.ItemService;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;
import com.petkpetk.service.domain.user.entity.ProfileImage;
import com.petkpetk.service.domain.user.service.UserAccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seller/my-page/")
public class SellerMyPageController {
	private final UserAccountService userAccountService;
	private final ItemService itemService;

	@GetMapping("/information")
	public String informationView(
		Model model, @AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal) {
		model.addAttribute("userAccount", userAccountService.getUserUpdateRequestView(userAccountPrincipal));
		//todo : profile 이미지가 없는 유저의 경우 null exception 발생
		ProfileImage profileImage = userAccountService.getUserProfile(userAccountPrincipal);
		model.addAttribute("profileImage", profileImage);
		return "my-page/seller/sellerMyPage";
	}

	@GetMapping(value = {"/item-manage", "/item-manage/{page}"})
	public String itemManage(
		ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model, Authentication authentication){
		String email = authentication.getName();

		PageRequest pageRequest = PageRequest.of(page.orElse(0), 5);

		Page<ManageItemDto> items = itemService.getItemList(itemSearchDto, pageRequest, email);

		model.addAttribute("items", items);
		model.addAttribute("itemSearchDto",itemSearchDto);
		model.addAttribute("maxPage", 5);
		return "my-page/seller/sellerItemList";

	}
}
