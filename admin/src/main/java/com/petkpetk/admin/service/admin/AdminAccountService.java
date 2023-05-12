package com.petkpetk.admin.service.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.admin.dto.AdminAccountDto;
import com.petkpetk.admin.dto.request.AdminSignupRequest;
import com.petkpetk.admin.dto.response.AdminAccountWaitingsResponse;
import com.petkpetk.admin.dto.security.AdminAccountPrincipal;
import com.petkpetk.admin.entity.AdminAccount;
import com.petkpetk.admin.exception.AdminAccountNotFoundException;
import com.petkpetk.admin.exception.EmailDuplicateException;
import com.petkpetk.admin.repository.AdminAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminAccountService {

	private final AdminAccountRepository adminAccountRepository;

	private final PasswordEncoder passwordEncoder;
	public Optional<AdminAccountDto> searchAdminDto(String email) {
		return adminAccountRepository.findByEmail(email).map(AdminAccountDto::fromEntity);
	}

	public void save(AdminSignupRequest adminSignupRequest) {
		String email = adminSignupRequest.getEmail();
		if (adminAccountRepository.existsByEmail(email)) {
			throw new EmailDuplicateException(email);
		}
		AdminAccount adminAccount = adminSignupRequest.toEntity();
		adminAccount.encodePassword(passwordEncoder);
		adminAccountRepository.save(adminAccount);
	}

	public boolean checkEmailDuplicate(String email) {
		return !adminAccountRepository.existsByEmail(email);
	}

	public boolean newPassword(String email, String previousPassword, String newPassword) {
		AdminAccount adminAccount = adminAccountRepository.findByEmail(email)
			.orElseThrow(AdminAccountNotFoundException::new);

		if(!adminAccount.checkPassword(previousPassword, passwordEncoder)){
			return false;
		};

		adminAccount.setPassword(newPassword);
		adminAccount.encodePassword(passwordEncoder);

		return true;
	}

	public void approve(Long id) {
		AdminAccount adminAccount = adminAccountRepository.findById(id)
			.orElseThrow(AdminAccountNotFoundException::new);
		adminAccount.approve();
	}

	public AdminAccountWaitingsResponse getAdminRegisterWaitings() {
		List<AdminAccountDto> adminAccounts = adminAccountRepository.findAdminAccountByApprovedFalse().stream().map(
			AdminAccountDto::fromEntity).collect(
			Collectors.toList());

		return AdminAccountWaitingsResponse.of(adminAccounts);
	}

	public AdminAccountDto getAdminAccount(Long id) {
		return adminAccountRepository.findById(id).map(AdminAccountDto::fromEntity).orElseThrow(AdminAccountNotFoundException::new);
	}

	public boolean checkPasswordMatch(String password, AdminAccountPrincipal adminAccountPrincipal) {
		AdminAccount adminAccount = adminAccountRepository.findByEmail(adminAccountPrincipal.getEmail())
			.orElseThrow(AdminAccountNotFoundException::new);

		return adminAccount.checkPassword(password, passwordEncoder);
	}


}



