package com.petkpetk.service.domain.shopping.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.domain.user.constant.RoleType;
import com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo;
import com.petkpetk.service.domain.shopping.constant.ItemStatus;
import com.petkpetk.service.domain.shopping.dto.item.ItemDto;
import com.petkpetk.service.domain.shopping.service.item.ItemService;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.entity.ProfileImage;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.entity.embedded.Address;

@Transactional
@SpringBootTest
class ItemServiceTest {

	@Autowired
	ItemService itemService;

	public MultipartFile createMultipartFile(String filePath) throws IOException {
		// 파일 객체를 생성함
		File file = new File(filePath);

		// 파일 이름과 내용을 읽어옴
		String fileName = file.getName();
		byte[] fileContent = Files.readAllBytes(file.toPath());

		// CommonsMultipartFile 객체를 생성하여 반환함

		return new MockMultipartFile(fileName, filePath, "multipart/form-data", fileContent);
	}

	// 상품 등록
	@Test
	void addItem() throws IOException {

		UserAccount userAccount = UserAccount.of("lee@email.com", "password", "이순신", "닉네임",
			Address.of("34589", "서울특별시 광진구", "자바동", "기타"), ProfileImage.of("/images/item/test.jpg"),
			OAuth2ProviderInfo.NAVER, Set.of(RoleType.USER));
		ItemDto itemDto =
			ItemDto.of("개껌", 1000L, 20.0, 10000L, 100L, "사람 간식으로 좋은", ItemStatus.SELL, null, UserAccountDto.from(userAccount), 10.0);

		String filePath = "C:\\KYE\\sample-project\\src\\main\\resources\\static\\img\\cat.png";

		MultipartFile multipartFile = createMultipartFile(filePath);

		List<MultipartFile> multipartFiles = new ArrayList<>();
		multipartFiles.add(multipartFile);

		itemService.registerItem(itemDto);
	}

	@Test
	void deleteItem() {
		itemService.deleteItem(1L);

	}

}