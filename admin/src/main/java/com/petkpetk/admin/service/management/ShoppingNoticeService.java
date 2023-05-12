package com.petkpetk.admin.service.management;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.admin.dto.response.ShoppingNoticeResponse;
import com.petkpetk.admin.dto.ShoppingNoticeDto;
import com.petkpetk.admin.entity.ShoppingNotice;
import com.petkpetk.admin.repository.ShoppingNoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ShoppingNoticeService {
	private final ShoppingNoticeRepository shoppingNoticeRepository;

	public List<ShoppingNoticeResponse> notices(){
		return shoppingNoticeRepository.findAll().stream().map(ShoppingNoticeResponse::from).collect(
			Collectors.toList());
	}

	public void registerNotice(ShoppingNoticeDto shoppingNoticeDto){
		shoppingNoticeRepository.save(ShoppingNotice.from(shoppingNoticeDto));
	}


}
