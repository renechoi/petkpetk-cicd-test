package com.petkpetk.service.domain.user.repository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.service.domain.user.constant.RoleType;
import com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo;
import com.petkpetk.service.domain.user.entity.ProfileImage;
import com.petkpetk.service.domain.user.entity.embedded.Address;
import com.petkpetk.service.domain.user.entity.UserAccount;

@Transactional
@DisplayName("UserAccountRepository 테스트")
@ExtendWith(MockitoExtension.class)
public class UserAccountRepositoryTest {
	@Mock
	private UserAccountRepository userAccountRepository;

	private UserAccount userAccount;

	@BeforeEach
	void setUp() {
		userAccount = UserAccount.of("lee@email.com", "password", "이순신", "닉네임",
			Address.of("34589", "서울특별시 광진구", "자바동", "기타"), ProfileImage.of("/images/item/test.jpg"),
			OAuth2ProviderInfo.NAVER, Set.of(RoleType.USER));
	}

	@DisplayName("유저 계정 저장을 테스트 한다")
	@Test
	void shouldSaveUserAccount() {
		// given
		when(userAccountRepository.save(any())).thenReturn(userAccount);

		// when
		userAccountRepository.save(userAccount);

		// then
		verify(userAccountRepository).save(userAccount);
	}

	@DisplayName("유저 계정 이메일 조회를 테스트 한다")
	@Test
	void shouldFindUserAccountByEmail() {
		// given
		when(userAccountRepository.findByEmail(any())).thenReturn(Optional.of(userAccount));

		// when
		Optional<UserAccount> result = userAccountRepository.findByEmail("lee@email.com");

		// then
		assertThat(result.isPresent()).isTrue();
		assertThat(result.get()).isEqualTo(userAccount);
	}

}
