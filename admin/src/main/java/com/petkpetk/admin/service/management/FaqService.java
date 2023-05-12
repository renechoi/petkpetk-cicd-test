package com.petkpetk.admin.service.management;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.admin.dto.FaqCategoryDto;
import com.petkpetk.admin.dto.FaqDto;
import com.petkpetk.admin.dto.ShoppingNoticeDto;
import com.petkpetk.admin.dto.response.FaqResponse;
import com.petkpetk.admin.dto.response.ShoppingNoticeResponse;
import com.petkpetk.admin.entity.Faq;
import com.petkpetk.admin.entity.FaqCategory;
import com.petkpetk.admin.entity.ShoppingNotice;
import com.petkpetk.admin.repository.FaqCategoryRepository;
import com.petkpetk.admin.repository.FaqRepository;
import com.petkpetk.admin.repository.ShoppingNoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FaqService {
	private final FaqRepository faqRepository;
	private final FaqCategoryRepository faqCategoryRepository;


	public List<FaqResponse> faqs(){
		return faqRepository.findAll().stream().map(FaqResponse::from).collect(
			Collectors.toList());
	}

	public void registerFaq(FaqDto faqDto){
		faqRepository.save(Faq.from(faqDto));
	}

	public void registerFaqCategory(FaqCategoryDto faqCategoryDto) {
		faqCategoryRepository.save(FaqCategory.from(faqCategoryDto));
	}

	public List<FaqCategoryDto> faqCategories() {
		return faqCategoryRepository.findAll().stream().map(FaqCategoryDto::from).collect(Collectors.toList());
	}
}
