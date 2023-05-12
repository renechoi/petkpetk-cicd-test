package com.petkpetk.service.domain.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.service.domain.user.constant.RoleType;
import com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.dto.request.UserSignupRequest;
import com.petkpetk.service.domain.user.entity.ProfileImage;
import com.petkpetk.service.domain.user.entity.embedded.Address;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.exception.UserDuplicateException;
import com.petkpetk.service.domain.user.repository.UserAccountRepository;

@Transactional
@DisplayName("UserAccountService 테스트")
public class UserAccountServiceTest {

	@InjectMocks
	private UserAccountService userAccountService;

	@Mock
	private UserAccountRepository userAccountRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	private UserAccountDto userAccountDto;
	private UserAccount userAccount;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		userAccountDto = UserAccountDto.of(1L, "email@email.com", "password", "홍길동", "길동", ProfileImage.of("/images/item/test.jpg"),
			Address.of("34589", "서울특별시 광진구", "자바동", "기타"), OAuth2ProviderInfo.GOOGLE, Set.of(RoleType.USER), "010-1234-1234", "페크페크", "123-1234-12345");
		userAccount = userAccountDto.toEntity();



		when(userAccountRepository.save(userAccount)).thenReturn(userAccount);
	}

	@Test
	@DisplayName("중복된 이메일이 있는 경우 UserAccountDuplicateException 예외를 던진다")
	public void saveTest_duplicateEmail() {
		// given
		when(userAccountRepository.findByEmail(any(String.class))).thenReturn(Optional.of(new UserAccount()));
		UserSignupRequest userSignupRequest = new UserSignupRequest(1L, "user@user.com", "password", "홍길동", "길동", null,
			Address.of("34589", "서울특별시 광진구", "자바동", "기타"), OAuth2ProviderInfo.GOOGLE, Set.of(RoleType.USER), "010-1234-1234", "페크페크", "123-1234-12345");


		// when, then
		assertThrows(UserDuplicateException.class, () -> userAccountService.save(userSignupRequest));

		verify(userAccountRepository, times(1)).findByEmail(any(String.class));
		verifyNoMoreInteractions(userAccountRepository);
	}

	@Test
	@DisplayName("중복된 이메일이 없는 경우 UserAccount 엔티티가 저장된다")
	public void saveTest() {
		// given
		when(userAccountRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
		when(passwordEncoder.encode(any(String.class))).thenReturn("encoded_password");
		when(userAccountRepository.save(any(UserAccount.class))).thenReturn(userAccount);
		UserSignupRequest userSignupRequest = new UserSignupRequest(1L, "new@user.com", "password", "홍길동", "길동", null,
			Address.of("34589", "서울특별시 광진구", "자바동", "기타"), OAuth2ProviderInfo.GOOGLE, Set.of(RoleType.USER), "010-1234-1234", "페크페크", "123-1234-12345");

		// when
		userAccountService.save(userSignupRequest);

		// then
		verify(userAccountRepository, times(1)).findByEmail(any(String.class));
		verify(userAccountRepository, times(1)).save(any(UserAccount.class));
		verifyNoMoreInteractions(userAccountRepository);
	}

	@Test
	@DisplayName("이메일로 사용자를 검색 한다")
	public void searchTest() {
		// given
		String email = userAccountDto.getEmail();
		when(userAccountRepository.findByEmail(email)).thenReturn(Optional.of(userAccount));

		// when
		UserAccountDto result = userAccountService.searchUserDto(email);

		// then
		assertThat(result).isNotNull();
		assertThat(result.getEmail()).isEqualTo(email);
	}
}
