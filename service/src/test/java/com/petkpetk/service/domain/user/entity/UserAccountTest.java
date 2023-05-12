package com.petkpetk.service.domain.user.entity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.service.config.security.PasswordEncoderConfig;
import com.petkpetk.service.domain.user.constant.RoleType;
import com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo;
import com.petkpetk.service.domain.user.entity.embedded.Address;

@Transactional
@ActiveProfiles("test")
@DisplayName("UserAccount 테스트")
@DataJpaTest
@ExtendWith(MockitoExtension.class)
@Import(PasswordEncoderConfig.class)
class UserAccountTest {

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserAccount userAccount;

	@Autowired
	private PasswordEncoderConfig passwordEncoderConfig;

	private String password = "password";

	@BeforeEach
	void setUp() {
		userAccount = UserAccount.of("lee@email.com", password, "이순신", "닉네임",
			Address.of("34589", "서울특별시 광진구", "자바동", "기타"), ProfileImage.of("/images/item/test.jpg"), OAuth2ProviderInfo.NAVER, Set.of(RoleType.USER));
	}

	@DisplayName("비밀번호 인코딩을 테스트 한다")
	@Test
	void shouldEncodePassword() {
		// given
		String encodedPassword = "encodedPassword";
		when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

		// when
		userAccount.encodePassword(passwordEncoder);

		// then
		verify(passwordEncoder).encode(password);
		assertEquals(encodedPassword, userAccount.getPassword());
	}

	@DisplayName("비밀번호 확인을 테스트 한다")
	@Test
	void shouldCheckPassword() {
		// given
		String thatPassword = "password";
		String encodedPassword = "encodedPassword";
		userAccount.setPassword(encodedPassword);

		when(passwordEncoder.matches(thatPassword, encodedPassword)).thenReturn(true);

		// when
		boolean result = userAccount.checkPassword(thatPassword, passwordEncoder);

		// then
		assertTrue(result);
		verify(passwordEncoder).matches(thatPassword, encodedPassword);
	}

	@DisplayName("유저 엔티티가 비밀번호를 인코딩하고 확인하는 것을 테스트 한다")
	@Test
	void shouldEncodeAndCheckPassword() {
		// given
		String givenPassword = "password";
		userAccount.setPassword(givenPassword);
		userAccount.encodePassword(passwordEncoderConfig.passwordEncoder());

		// when
		boolean result = userAccount.checkPassword(givenPassword, passwordEncoderConfig.passwordEncoder());

		// then
		Assertions.assertTrue(result);
	}
}
