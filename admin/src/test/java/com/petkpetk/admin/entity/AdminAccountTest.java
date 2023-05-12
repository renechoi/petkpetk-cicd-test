package com.petkpetk.admin.entity;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.petkpetk.admin.config.constant.RoleType;
import com.petkpetk.admin.config.security.PasswordEncoderConfig;
import com.petkpetk.admin.repository.AdminAccountRepository;

@ActiveProfiles("test")
@DisplayName("AdminAccount 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@Import(PasswordEncoderConfig.class)
class AdminAccountTest {

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private AdminAccountRepository adminAccountRepository;

	@DisplayName("비밀번호 암호화 성공")
	@Test
	void should_EncodePassword() {
		// given
		String password = "test1234";
		given(passwordEncoder.encode(password)).willAnswer(invocation -> "encoded_" + password);

		// when
		String encodedPassword1 = passwordEncoder.encode(password);
		String encodedPassword2 = passwordEncoder.encode(password);

		// then
		assertThat(encodedPassword1).isNotEqualTo(password);
		assertThat(encodedPassword2).isNotEqualTo(password);
		assertThat(encodedPassword1).isEqualTo(encodedPassword2);
		assertThat(encodedPassword1).isEqualTo("encoded_test1234");
	}

	@DisplayName("Equals and HashCode 재정의에 따른 동등성 비교 성공")
	@Test
	void should_ReturnTrue_When_EqualsAndHashCodeAreSame() {
		// given
		AdminAccount adminAccount1 = AdminAccount.of("test@example.com", "test1234", "testUser",
			Set.of(RoleType.ADMIN));
		AdminAccount adminAccount2 = AdminAccount.of("test@example.com", "test5678", "testUser",
			Set.of(RoleType.ADMIN));

		// when
		boolean isEqual = adminAccount1.equals(adminAccount2);

		// then
		assertThat(isEqual).isTrue();
	}

}
