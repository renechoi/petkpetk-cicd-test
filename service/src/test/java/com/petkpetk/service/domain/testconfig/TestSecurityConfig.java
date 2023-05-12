package com.petkpetk.service.domain.testconfig;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.Set;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.petkpetk.service.config.security.OAuth2Config;
import com.petkpetk.service.config.security.SecurityConfig;
import com.petkpetk.service.domain.user.constant.RoleType;
import com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.dto.request.UserSignupRequest;
import com.petkpetk.service.domain.user.entity.ProfileImage;
import com.petkpetk.service.domain.user.entity.embedded.Address;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.service.UserAccountService;

@Import({SecurityConfig.class, OAuth2Config.class})
@SpringBootTest
public class TestSecurityConfig {
	@MockBean
	// @Autowired
	private UserAccountService userAccountService;

	@BeforeTestMethod
	public void securitySetUp() {
		// 코드를 통해 인증된 사용자로 설정된 사용자 객체가 존재하는 것으로 가정
		given(userAccountService.searchUserDto(anyString())).willReturn(UserAccountDto.from(createUserAccount()));

		// given(userAccountService.searchUser(anyString())).willReturn(Optional.empty());

		doNothing().when(userAccountService).save(any(UserSignupRequest.class));

	}

	private UserAccountDto createUserAccountDto() {
		return UserAccountDto.of(1L, "email@email.com", "password", "홍길동", "길동", ProfileImage.of("/images/item/test.jpg"),
			Address.of("34589", "서울특별시 광진구", "자바동", "기타"), OAuth2ProviderInfo.GOOGLE, Set.of(RoleType.USER), "010-1234-1234", "페크페크", "123-1234-12345");
	}

	private UserAccount createUserAccount() {
		return UserAccount.of("lee@email.com", "password", "이순신", "닉네임", Address.of("34589", "서울특별시 광진구", "자바동", "기타"),
			ProfileImage.of("/images/item/test.jpg"), OAuth2ProviderInfo.NAVER, Set.of(RoleType.USER));
	}

}

