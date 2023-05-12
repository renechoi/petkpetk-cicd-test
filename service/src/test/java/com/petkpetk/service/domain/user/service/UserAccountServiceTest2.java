package com.petkpetk.service.domain.user.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.service.domain.user.constant.RoleType;
import com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.dto.request.UserSignupRequest;
import com.petkpetk.service.domain.user.dto.request.UserUpdateRequest;
import com.petkpetk.service.domain.user.entity.ProfileImage;
import com.petkpetk.service.domain.user.entity.embedded.Address;
import com.petkpetk.service.domain.user.exception.UserDuplicateException;
import com.petkpetk.service.domain.user.repository.UserAccountRepository;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Transactional
class UserAccountServiceTest2 {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private UserAccountService userAccountService;

	@DisplayName("실제 db 테스트 - 이메일이 db에 있는 경우 UserAccount를 반환한다")
	@Test
	void findByEmail_returnsUserAccount_whenEmailExistsInDB() {
		// given
		String email = "user@user.com";
		UserAccountDto userAccountDto = new UserAccountDto();
		userAccountDto.setEmail(email);

		// when
		UserAccountDto optionalUserAccount = userAccountService.searchUserDto(userAccountDto.getEmail());

		// then
		assertEquals("user@user.com", optionalUserAccount.getEmail());
	}

	@DisplayName("실제 db 테스트 - 이메일이 db에 없는 경우 빈 Optional을 반환한다")
	@Test
	void findByEmail_returnsEmpty_whenEmailDoesNotExistInDB() {
		// given
		String email = "nonexistent@user.com";
		UserAccountDto userAccountDto = new UserAccountDto();
		userAccountDto.setEmail(email);

		// when
		UserAccountDto optionalUserAccount = userAccountService.searchUserDto(userAccountDto.getEmail());

		// then
	}

	@DisplayName("실제 db 테스트 - 정상 회원가입을 테스트 한다")
	@Test
	void save_createsNewUserAccount() {
		// given
		UserAccountDto newUserAccountDto = UserAccountDto.of(1L, "newuser@user.com", "password", "홍길동", "길동", ProfileImage.of("/images/item/test.jpg"),
			Address.of("34589", "서울특별시 광진구", "자바동", "기타"), OAuth2ProviderInfo.GOOGLE, Set.of(RoleType.USER), "010-1234-1234", "페크페크", "123-1234-12345");
		// when

		UserSignupRequest userSignupRequest = new UserSignupRequest(1L, "newuser@user.com", "password", "홍길동", "길동", null,
			Address.of("34589", "서울특별시 광진구", "자바동", "기타"), OAuth2ProviderInfo.GOOGLE, Set.of(RoleType.USER), "010-1234-1234", "페크페크", "123-1234-12345");
		userAccountService.save(userSignupRequest);

		// then
		UserAccountDto userAccountDto = userAccountService.searchUserDto("newuser@user.com");
		assertEquals("newuser@user.com", userAccountDto.getEmail());
		assertEquals("password", userAccountDto.getPassword());
		assertEquals("새로운 유저", userAccountDto.getName());
		assertEquals("newuser", userAccountDto.getNickname());
		assertEquals("12345", userAccountDto.getAddress().getZipCode());
		assertEquals("강남", userAccountDto.getAddress().getAddress1());
		assertEquals("2", userAccountDto.getAddress().getAddress2());
		assertEquals("etc", userAccountDto.getAddress().getAddressEtc());
		assertEquals("profile.jpg", userAccountDto.getProfileImage());
		assertEquals(OAuth2ProviderInfo.NAVER, userAccountDto.getOAuth2ProviderInfo());
		assertEquals(Set.of(RoleType.USER), userAccountDto.getRoles());
	}

	@Test
	@DisplayName("실제 db 테스트 - 중복 가입 이메일로 회원가입에 실패한다")
	void saveTest_userAccountDuplicateException() {
		// given
		UserSignupRequest userSignupRequest = new UserSignupRequest(1L, "user@user.com", "password", "홍길동", "길동", null,
			Address.of("34589", "서울특별시 광진구", "자바동", "기타"), OAuth2ProviderInfo.GOOGLE, Set.of(RoleType.USER), "010-1234-1234", "페크페크", "123-1234-12345");

		// when & then
		assertThrows(UserDuplicateException.class, () -> userAccountService.save(userSignupRequest));
	}

	@DisplayName("실제 db 테스트 - 기존 사용자 계정 업데이트 한다")
	@Test
	void update_updatesExistingUserAccount() {

		// given
		String email = "user@user.com";
		UserUpdateRequest userUpdateRequest = new UserUpdateRequest(1L, email, "password", "홍길동", "길동", null,
			Address.of("34589", "서울특별시 광진구", "자바동", "기타"), OAuth2ProviderInfo.GOOGLE, Set.of(RoleType.USER), "010-1234-1234", "페크페크", "123-1234-12345");


		// when
		userAccountService.update(userUpdateRequest, false);

		// then
		UserAccountDto userAccountDto = userAccountService.searchUserDto(email);
		assertEquals("user@user.com", userAccountDto.getEmail());
		assertEquals("new_password", userAccountDto.getPassword());
		assertEquals("새로운 유저", userAccountDto.getName());
		assertEquals("newuser", userAccountDto.getNickname());
		assertEquals("54321", userAccountDto.getAddress().getZipCode());
		assertEquals("강서", userAccountDto.getAddress().getAddress1());
		assertEquals("3", userAccountDto.getAddress().getAddress2());
		assertEquals("etc", userAccountDto.getAddress().getAddressEtc());
		assertEquals("new_profile.jpg", userAccountDto.getProfileImage());
		assertEquals(Set.of(RoleType.ADMIN), userAccountDto.getRoles());
	}

}