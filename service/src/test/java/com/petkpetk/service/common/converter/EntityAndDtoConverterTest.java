package com.petkpetk.service.common.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.petkpetk.service.config.converter.EntityAndDtoConverter;
import com.petkpetk.service.domain.user.constant.RoleType;
import com.petkpetk.service.config.security.oauth2.OAuth2ProviderInfo;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.entity.ProfileImage;
import com.petkpetk.service.domain.user.entity.embedded.Address;
import com.petkpetk.service.domain.user.entity.UserAccount;

@DisplayName("EntityAndDtoConverter 테스트")
@ExtendWith(MockitoExtension.class)
public class EntityAndDtoConverterTest {

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private EntityAndDtoConverter entityAndDtoConverter;

	UserAccount userAccount = UserAccount.of("lee@email.com", "password", "이순신", "닉네임",
		Address.of("30302", "서울특별시 광진구", "자바동", "기타"), ProfileImage.of("/images/item/test.jpg"),
		OAuth2ProviderInfo.NAVER, Set.of(RoleType.USER));

	@DisplayName("Dto -> Entity 정상 변환을 테스트 한다")
	@Test
	public void testConvertToEntity() {

		// given
		userAccount.setId(1L);
		UserAccountDto userAccountDto = UserAccountDto.fromEntity(userAccount);

		// when
		UserAccount convertedUserAccount = EntityAndDtoConverter.convertToEntity(userAccountDto, UserAccount.class);

		// then
		assertEquals(userAccount, convertedUserAccount);
	}

	@DisplayName("Entity -> Dto 정상 변환을 테스트 한다")
	@Test
	public void testConvertToDto() {
		// given
		UserAccountDto expectedUserAccountDto = UserAccountDto.fromEntity(userAccount);

		// when
		UserAccountDto convertedUserAccountDto = EntityAndDtoConverter.convertToDto(userAccount, UserAccountDto.class);

		// then
		assertEquals(expectedUserAccountDto, convertedUserAccountDto);
	}

	@DisplayName("Dto -> Entity 몇가지 예외 조건시 변환을 테스트 한다")
	@Test
	public void testConvertToEntity_withChangedFields() {
		// given
		UserAccountDto userAccountDto = UserAccountDto.fromEntity(userAccount);
		userAccountDto.setEmail("kim@email.com");
		userAccountDto.setNickname("새로운닉네임");

		// when
		UserAccount convertedUserAccount = EntityAndDtoConverter.convertToEntity(userAccountDto, UserAccount.class);

		// then
		assertNotEquals(userAccount, convertedUserAccount);
		assertEquals(userAccount.getAddress(), convertedUserAccount.getAddress());
		assertEquals(userAccount.getPassword(), convertedUserAccount.getPassword());
		assertEquals(userAccount.getProfileImage(), convertedUserAccount.getProfileImage());
		assertEquals(userAccount.getRoles(), convertedUserAccount.getRoles());
		assertEquals(userAccount.getName(), convertedUserAccount.getName());
		assertEquals(userAccount.getOAuth2ProviderInfo(), convertedUserAccount.getOAuth2ProviderInfo());
		assertEquals(userAccount.getId(), convertedUserAccount.getId());
		assertEquals(userAccountDto.getEmail(), convertedUserAccount.getEmail());
		assertEquals(userAccountDto.getNickname(), convertedUserAccount.getNickname());
	}

	@DisplayName("Entity -> Dto 몇가지 예외 조건시 변환을 테스트 한다")
	@Test
	void testConvertToDto_withChangedFields() {
		// given
		userAccount.setId(1L);
		userAccount.setPassword("newPassword");
		userAccount.setAddress(Address.of("34589", "서울특별시 광진구", "자바동", "기타"));
		userAccount.setRoles(Set.of(RoleType.USER, RoleType.ADMIN));

		// when
		UserAccountDto userAccountDto = UserAccountDto.fromEntity(userAccount);

		// then
		assertAll("UserAccountDto fields are mapped correctly",
			() -> assertEquals(userAccount.getId(), userAccountDto.getId()),
			() -> assertEquals(userAccount.getEmail(), userAccountDto.getEmail()),
			() -> assertEquals(userAccount.getPassword(), userAccountDto.getPassword()), // password가 변경되어도 매핑이 잘 이루어지는지 확인
			() -> assertEquals(userAccount.getName(), userAccountDto.getName()),
			() -> assertEquals(userAccount.getNickname(), userAccountDto.getNickname()),
			() -> assertEquals(userAccount.getAddress(), userAccountDto.getAddress()), // address가 변경되어도 매핑이 잘 이루어지는지 확인
			() -> assertEquals(userAccount.getProfileImage(), userAccountDto.getProfileImage()),
			() -> assertEquals(userAccount.getOAuth2ProviderInfo(), userAccountDto.getOAuth2ProviderInfo()),
			() -> assertEquals(userAccount.getRoles(), userAccountDto.getRoles()) // roles가 변경되어도 매핑이 잘 이루어지는지 확인
		);
	}

}
