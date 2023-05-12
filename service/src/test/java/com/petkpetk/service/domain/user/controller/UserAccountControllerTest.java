package com.petkpetk.service.domain.user.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.petkpetk.service.config.security.oauth2.provider.GoogleOAuth2Provider;
import com.petkpetk.service.config.security.oauth2.provider.KakaoOAuth2Provider;
import com.petkpetk.service.config.security.oauth2.provider.NaverOAuth2Provider;
import com.petkpetk.service.domain.testconfig.TestSecurityConfig;
import com.petkpetk.service.domain.user.repository.UserAccountRepository;
import com.petkpetk.service.domain.user.service.SocialUserAccountService;
import com.petkpetk.service.domain.user.service.UserAccountService;

@ActiveProfiles("test")
@Import({TestSecurityConfig.class, GoogleOAuth2Provider.class, KakaoOAuth2Provider.class, NaverOAuth2Provider.class,
	SocialUserAccountService.class})
@WebMvcTest(UserAccountController.class)
public class UserAccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// @MockBean
	@Autowired
	private UserAccountService userAccountService;

	@MockBean
	private UserAccountRepository userAccountRepository;

	@DisplayName("로그인 페이지 - 정상 호출")
	@WithAnonymousUser
	@Test
	public void givenNothing_whenTryingToLogIn_thenReturnsLogInView() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/user/login"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
		then(userAccountService).shouldHaveNoInteractions();
	}

	@DisplayName("회원가입 페이지 - 정상 호출")
	@WithAnonymousUser
	@Test
	public void givenNothing_whenTryingToAccessSignUpPage_thenReturnsSignUpPage() throws Exception {
		mockMvc.perform(get("/user/sign-up"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
			.andExpect(view().name("user/sign-up"))
			.andExpect(model().attributeExists("userAccount"));
		then(userAccountService).shouldHaveNoInteractions();
	}

}
