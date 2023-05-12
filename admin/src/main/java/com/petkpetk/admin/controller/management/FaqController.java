package com.petkpetk.admin.controller.management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petkpetk.admin.controller.ApiController;
import com.petkpetk.admin.dto.FaqCategoryDto;
import com.petkpetk.admin.dto.FaqDto;
import com.petkpetk.admin.dto.ShoppingNoticeDto;
import com.petkpetk.admin.dto.api.ResponseDTO;
import com.petkpetk.admin.dto.request.FaqCategoryRequest;
import com.petkpetk.admin.dto.request.FaqRequest;
import com.petkpetk.admin.dto.request.ShoppingNoticeRegisterRequest;
import com.petkpetk.admin.service.management.FaqService;
import com.petkpetk.admin.service.management.ShoppingNoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/management/faq")
public class FaqController extends ApiController {
	private final FaqService faqService;

	@GetMapping("/register")
	public String registerFaq(Model model) {
		model.addAttribute("faqs", new FaqRequest());
		model.addAttribute("categories", faqService.faqCategories());
		return "main/management/faq-registerform";
	}


	@ResponseBody
	@PostMapping("/register")
	public ResponseDTO<Void> registerFaq(FaqRequest faqRequest){
		faqService.registerFaq(FaqDto.from(faqRequest));
		return ok();
	}

	@GetMapping
	public String faqs(Model model){
		model.addAttribute("faqs", faqService.faqs());
		return "main/management/faq";
	}


	@ResponseBody
	@PostMapping("/category/register")
	public ResponseDTO<Void> registerFaqCategory(FaqCategoryRequest faqCategoryRequest){
		faqService.registerFaqCategory(FaqCategoryDto.from(faqCategoryRequest));
		return ok();
	}


}
