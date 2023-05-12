package com.petkpetk.service.domain.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petkpetk.service.domain.user.dto.UserAskDto;
import com.petkpetk.service.domain.user.entity.UserAsk;

public interface UserAskRepository extends JpaRepository<UserAsk, Long> {

	List<UserAsk> findAllByUserAccountIdOrderById(Long userAccountId);
}

