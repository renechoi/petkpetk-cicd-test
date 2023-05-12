package com.petkpetk.admin.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.petkpetk.admin.config.constant.RoleType;
import com.petkpetk.admin.config.converter.EntityAndDtoConverter;
import com.petkpetk.admin.config.security.PasswordEncoderConfig;
import com.petkpetk.admin.dto.request.AdminSignupRequest;
import com.petkpetk.admin.entity.AdminAccount;
import com.petkpetk.admin.exception.EmailDuplicateException;
import com.petkpetk.admin.repository.AdminAccountRepository;
import com.petkpetk.admin.service.admin.AdminAccountService;

@ActiveProfiles("test")
@DisplayName("AdminAccountService 테스트")
@ExtendWith(MockitoExtension.class)
@Import({PasswordEncoderConfig.class, EntityAndDtoConverter.class})
public class AdminAccountServiceTest {

	@InjectMocks
	AdminAccountService adminAccountService;

	@Mock
	private AdminAccountRepository adminAccountRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Spy
	private EntityAndDtoConverter entityAndDtoConverter;

	private AdminAccount adminAccount;

	@BeforeEach
	void setUp() {
		adminAccount = AdminAccount.of(1L, "test@example.com", "test1234", "testUser", Set.of(RoleType.ADMIN));
	}

	@Test
	@DisplayName("회원가입 정상 동작")
	void should_SignupSuccess_When_SignupRequest() {
		// given
		AdminSignupRequest adminSignupRequest = new AdminSignupRequest("test@example.com", "test1234", "testUser");

		// when
		adminAccountService.save(adminSignupRequest);

		// then
		verify(adminAccountRepository, times(1)).save(any());
	}

	@Test
	@DisplayName("회원가입 실패 - 같은 이메일을 사용하는 유저가 존재함")
	void should_SignupFailureWithSameEmailAccount_When_SignupRequest() {

		// given
		String email = "test@example.com";
		AdminSignupRequest adminSignupRequest = new AdminSignupRequest(email, "test1234", "testUser");
		AdminAccount existingAccount = AdminAccount.of(email, "encodedPassword", "testUser", Set.of(RoleType.ADMIN));
		when(adminAccountRepository.existsByEmail(email)).thenReturn(true);

		// when
		Throwable throwable = assertThrows(EmailDuplicateException.class, () -> adminAccountService.save(adminSignupRequest));

		// then
		verify(adminAccountRepository, never()).save(any());
		assertThat(throwable).isInstanceOf(EmailDuplicateException.class);
	}


}




