package com.petkpetk.admin.controller;// package com.petkpetk.admin.controller;
//
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.BDDMockito.*;
//
// import java.util.Optional;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.Import;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.context.event.annotation.BeforeTestMethod;
// import org.springframework.test.web.servlet.MockMvc;
//
// import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
// import com.petkpetk.admin.config.security.PasswordEncoderConfig;
// import com.petkpetk.admin.service.admin.AdminAccountService;
//
// @ActiveProfiles("test")
// @DisplayName("AdminAccountController 테스트")
// @ExtendWith(MockitoExtension.class)
// @Import({PasswordEncoderConfig.class, EntityAndDtoConverter.class})
// public class AdminAccountControllerTest {
//
// 	private final MockMvc mvc;
//
// 	@MockBean
// 	private AdminAccountService adminAccountService;
//
// 	public AdminAccountControllerTest(@Autowired MockMvc mvc) {
// 		this.mvc = mvc;
// 	}
//
// 	@BeforeTestMethod
// 	public void securitySetup() {
// 		// given(adminAccountService.searchUser(anyString())).willReturn(Optional.of(createAdminAccountDto()));
// 		// given(adminAccountService.saveUser(anyString(), anyString(), anySet(), anyString(), anyString(), anyString()))
// 		// 	.willReturn(createAdminAccountDto());
// 	}
//
//
//
// 	@Test
// 	@DisplayName("어드민 회원 가입 페이지 정상 호춣")
// 	void should_RenderAdminSignupPageSuccess() {
//
// 		// todo
//
// 	}
//
//
//
//
// 	@Test
// 	@DisplayName("로그인 정상 동작")
// 	void should_LoginSuccess_When_LoginRequest() {
//
// 		// todo
//
// 	}
//
//
// 	@Test
// 	@DisplayName("로그인 실패 - 해당하는 유저(이메일)가 존재하지 않음")
// 	void should_LoginFailureWithEmailNotExists_When_LoginRequest() {
//
// 	}
//
// 	@Test
// 	@DisplayName("로그인 실패 - 패스워드가 일치하지 않음")
// 	void should_LoginFailureWithPasswordNotMatching_When_LoginRequest() {
//
// 	}
// }
