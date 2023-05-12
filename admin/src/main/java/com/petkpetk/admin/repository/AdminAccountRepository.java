package com.petkpetk.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petkpetk.admin.entity.AdminAccount;

public interface AdminAccountRepository extends JpaRepository<AdminAccount, Long> {

	Optional<AdminAccount> findByEmail(String email);

	boolean existsByEmail(String email);

	// todo : approved 가 false 인 adminAccounts를 구하기
	List<AdminAccount> findAdminAccountByApprovedFalse();

}
