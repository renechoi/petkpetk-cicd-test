package com.petkpetk.admin.controller.management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petkpetk.admin.controller.ApiController;
import com.petkpetk.admin.dto.ShoppingNoticeDto;
import com.petkpetk.admin.dto.api.ResponseDTO;
import com.petkpetk.admin.dto.request.ShoppingNoticeRegisterRequest;
import com.petkpetk.admin.service.management.ShoppingNoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/management/shopping-notice")
public class ShoppingNoticeController extends ApiController {
	private final ShoppingNoticeService shoppingNoticeService;

	@GetMapping("/register")
	public String registerNotice(Model model){
		model.addAttribute("notices", new ShoppingNoticeRegisterRequest());
		return "main/management/shopping-notice-registerform";
	}

	@ResponseBody
	@PostMapping("/register")
	public ResponseDTO<Void> registerNotice(ShoppingNoticeRegisterRequest shoppingNoticeRegisterRequest){
		// todo : 이미지 등록 추후 구현
		shoppingNoticeService.registerNotice(ShoppingNoticeDto.from(shoppingNoticeRegisterRequest));
		return ok();
	}

	@GetMapping
	public String notices(Model model){
		model.addAttribute("notices", shoppingNoticeService.notices());
		return "main/management/shopping-notice";
	}


}
