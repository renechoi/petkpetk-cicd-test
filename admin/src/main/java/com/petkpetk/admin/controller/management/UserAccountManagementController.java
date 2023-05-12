package com.petkpetk.admin.controller.management;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petkpetk.admin.dto.response.UserAccountResponse;
import com.petkpetk.admin.service.management.UserAccountManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("management/user-account")
public class UserAccountManagementController {

	private final UserAccountManagementService userAccountManagementService;

	@GetMapping()
	public String userAccounts(Model model) {
		model.addAttribute("userAccounts", userAccountManagementService.getUserAccounts()
			.stream()
			.map(UserAccountResponse::from)
			.collect(Collectors.toList()));

		return "main/management/user-management";
	}

	@ResponseBody
	@GetMapping("/{id}")
	public UserAccountResponse userAccount(@PathVariable String id) {
		return UserAccountResponse.from(userAccountManagementService.getUserAccount(id));
	}

	@PostMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		userAccountManagementService.deleteUserAccount(id);
		return ResponseEntity.ok("삭제 성공");

	}
}