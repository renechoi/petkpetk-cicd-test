package com.petkpetk.service.domain.shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.domain.shopping.dto.cart.request.CartItemRegisterRequest;
import com.petkpetk.service.domain.shopping.dto.item.ItemDto;
import com.petkpetk.service.domain.shopping.dto.item.ItemImageDto;
import com.petkpetk.service.domain.shopping.dto.item.request.ItemRegisterRequest;
import com.petkpetk.service.domain.shopping.dto.item.response.ItemResponse;
import com.petkpetk.service.domain.shopping.dto.order.CheckoutDto;
import com.petkpetk.service.domain.shopping.dto.order.request.CheckoutRequest;
import com.petkpetk.service.domain.shopping.dto.review.request.ReviewRegisterRequest;
import com.petkpetk.service.domain.shopping.dto.review.response.ReviewResponse;
import com.petkpetk.service.domain.shopping.service.item.ItemService;
import com.petkpetk.service.domain.shopping.service.review.ReviewService;
import com.petkpetk.service.domain.shopping.service.review.likes.ReviewLikesService;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;
import com.petkpetk.service.domain.user.service.UserAccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

	private final ItemService itemService;
	private final UserAccountService userAccountService;
	private final ReviewLikesService reviewLikesService;
	private final ReviewService reviewService;

	@GetMapping("/my-page")
	public String myPageView() {
		return "my-page/seller/sellerMyPage";
	}

	@GetMapping("/new")
	public String registerItem(Model model) {
		model.addAttribute("item", new ItemRegisterRequest());
		return "item/itemApply";
	}

	@PostMapping("/new")
	public String registerItem(@Valid ItemRegisterRequest itemRegisterRequest,
		@AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal) {
		itemRegisterRequest.setTotalRating(5.0);
		itemService.registerItem(
			ItemDto.from(itemRegisterRequest, userAccountPrincipal.toDto()));
		return "redirect:/";
	}

	@GetMapping("/{itemId}")
	public String itemDetail(Model model, @PathVariable("itemId") Long itemId, Authentication authentication) {
		ItemResponse itemResponse = itemService.getItemDetail(itemId);
		UserAccountDto itemUser = userAccountService.searchUserDto(itemResponse.getUserAccountDto().getEmail());

		String email = "";
		if (authentication != null && authentication.isAuthenticated()) {
			email = authentication.getName();
			model.addAttribute("userEmail", email);
			UserAccountDto userAccount = userAccountService.searchUserDto(email);
			HashMap<String, String> hashMap;
			hashMap = reviewLikesService.findHistoryLikeByUser(userAccount.getId());
			model.addAttribute("reviewHashMap", hashMap);
		}
		List<ReviewResponse> reviewList = reviewService.getReviewList(itemId);

		model.addAttribute("item", itemResponse);
		model.addAttribute("order",new CheckoutRequest(List.of(CheckoutDto.of(itemResponse.getId()))));
		model.addAttribute("cart", new CartItemRegisterRequest());
		model.addAttribute("itemUser", itemUser);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("reviewDtos", new ReviewRegisterRequest());
		return "item/itemDetail";

	}

	@GetMapping("/modify/{itemId}")
	public String modifyItem(@PathVariable("itemId") Long itemId, Model model) {
		ItemResponse itemResponse = itemService.getItemDetail(itemId);
		model.addAttribute("item", itemResponse);
		return "item/itemApply";
	}

	@PostMapping("/modify/{itemId}")
	public String modifyItem(
		ItemRegisterRequest itemUpdateRequest,
		BindingResult bindingResult, Model model,
		@RequestParam("images") List<MultipartFile> rawImages,
		@RequestParam("imageNames") List<String> imageNames,
		@RequestParam("uniqueImageNames") List<String> uniqueImageNames
	) {

		if (bindingResult.hasErrors()) {
			log.info("errors = {}", bindingResult);
			return "redirect:/seller/item-manage";
		}

		// TODO: 대표이미지 정하라는 메시지는 register랑 다르게 나가야 함

		// TODO: requestParam으로 별도로 받는 images, imagesNames는 itemRequest 객체에서 필드로 images와 itemDtos를 갖고 있기 때문에 별도로 받을 필요가 없다.
		//  하지만 해당 fields는 collection으로 존재하기 때문에 내부 객체가 개수로서 정의되어 있지 않고 따라서 각 객체마다 별도 초기화가 필요하다.
		//  이와 같은 이유 때문에 thymeleaf에서 name 값으로
		//   name="itemImageDtos[${status.index}].originalName" 주는 방식이 불가능하다.
		//  이점을 추후 리팩토링 과제로 삼아볼만하다고 생각되며, 더불어서 이 방식이 가능하다며 현재 itemResponse로 송추되는 데이터 객체를 request로 변경하는 것이 바람직하겠다

		// todo : original 네임은 필요 없음 !!! 추후 리팩토링
		itemUpdateRequest.setImages(rawImages);
		IntStream.range(0, imageNames.size())
			.filter(i -> !imageNames.get(i).equals("첨부파일"))
			.forEach(i -> itemUpdateRequest.getItemImageDtos()
				.add(ItemImageDto.of(imageNames.get(i), uniqueImageNames.get(i))));

		ItemResponse itemResponse = itemService.updateItem(itemUpdateRequest);
		model.addAttribute("item", itemResponse);

		return "redirect:/item/" + itemUpdateRequest.getId();
	}

	@GetMapping("/delete/{itemId}")
	public String deleteItem(@PathVariable("itemId") Long itemId) {
		itemService.deleteItem(itemId);
		return "redirect:/seller/item-manage";
	}

}
