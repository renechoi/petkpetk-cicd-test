package com.petkpetk.service.domain.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.service.domain.user.entity.UserAccount;

@Transactional
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class userAccountRepositoryTest2 {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Test
	@DisplayName("실제 db 테스트 - 이메일로 사용자를 조회한다")
	void findByEmail() {
		// given
		String email = "user@user.com";

		// when
		Optional<UserAccount> userAccountOptional = userAccountRepository.findByEmail(email);

		// then
		assertTrue(userAccountOptional.isPresent());
		UserAccount userAccount = userAccountOptional.get();
		assertEquals(email, userAccount.getEmail());
	}

	@Test
	@DisplayName("실제 db 테스트 - 이메일로 사용자를 삭제한다")
	void deleteUserAccount() {
		// given
		String email = "user@user.com";
		Optional<UserAccount> userAccountOptional = userAccountRepository.findByEmail(email);

		// when
		userAccountOptional.ifPresent(userAccountRepository::delete);

		// then
		Optional<UserAccount> deletedUserAccountOptional = userAccountRepository.findByEmail(email);
		assertFalse(deletedUserAccountOptional.isPresent());
	}

	@Test
	@DisplayName("실제 db 테스트 - 이메일로 사용자를 업데이트한다")
	void updateUserAccount() {
		// given
		String email = "user@user.com";
		Optional<UserAccount> userAccountOptional = userAccountRepository.findByEmail(email);
		String newName = "박";

		// when
		userAccountOptional.ifPresent(userAccount -> {
			userAccount.setName(newName);
			userAccountRepository.save(userAccount);
		});

		// then
		Optional<UserAccount> updatedUserAccountOptional = userAccountRepository.findByEmail(email);
		assertTrue(updatedUserAccountOptional.isPresent());
		UserAccount updatedUserAccount = updatedUserAccountOptional.get();
		assertEquals(newName, updatedUserAccount.getName());
	}
}
