package com.petkpetk.service.domain.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petkpetk.service.domain.shopping.exception.UserAskNotFountException;
import com.petkpetk.service.domain.user.dto.UserAccountDto;
import com.petkpetk.service.domain.user.dto.UserAskDto;
import com.petkpetk.service.domain.user.dto.request.UserAskRequest;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;
import com.petkpetk.service.domain.user.entity.UserAccount;
import com.petkpetk.service.domain.user.entity.UserAsk;
import com.petkpetk.service.domain.user.repository.UserAskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAskService {

	private final UserAskRepository userAskRepository;

	public void saveAsk(UserAskRequest userAskRequest, UserAccountDto userAccountDto) {
		UserAsk userAsk = userAskRequest.toEntity();
		userAsk.mapUserAccount(UserAccount.from(userAccountDto));
		userAskRepository.save(userAsk);
	}

	public List<UserAskDto> getUserAsks(UserAccountPrincipal userAccountPrincipal) {
		return userAskRepository.findAllByUserAccountIdOrderById(userAccountPrincipal.getId()).stream().map(UserAskDto::from).collect(
			Collectors.toList());
	}

	public void deleteAsk(Long askId) {
		UserAsk userAsk = userAskRepository.findById(askId).orElseThrow(UserAskNotFountException::new);
		userAsk.setDeletedYn("Y");
		userAskRepository.flush();
	}
}
