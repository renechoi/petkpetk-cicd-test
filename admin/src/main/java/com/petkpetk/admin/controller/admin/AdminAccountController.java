package com.petkpetk.admin.controller.admin;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.petkpetk.admin.dto.request.AdminSignupRequest;
import com.petkpetk.admin.dto.response.AdminAccountResponse;
import com.petkpetk.admin.dto.security.AdminAccountPrincipal;
import com.petkpetk.admin.service.admin.AdminAccountService;
import com.petkpetk.admin.service.admin.EmailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminAccountController {

	private final AdminAccountService adminAccountService;
	private final EmailService emailService;

	@GetMapping("/login")
	public String admin() {
		return "main/admin-account/login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return "redirect:/login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("adminSignupRequest", new AdminSignupRequest());
		return "main/admin-account/register";
	}

	@PostMapping("/register")
	public String register(@Valid AdminSignupRequest adminSignupRequest, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("bindingResult", bindingResult);
			return "main/admin-account/register";
		}
		adminAccountService.save(adminSignupRequest);

		return "redirect:/";
	}

	@GetMapping("/approve-register")
	public String approveRegister(Model model){
		model.addAttribute("adminAccounts",
			adminAccountService.getAdminRegisterWaitings().getAdminAccounts().stream().map(
			AdminAccountResponse::from).collect(
			Collectors.toList()));

		return "main/admin-account/approve-register";
	}


	@ResponseBody
	@GetMapping("/approve-register/{id}")
	public AdminAccountResponse approveRegisterAdminDetail(@PathVariable Long id) {
		return AdminAccountResponse.from(adminAccountService.getAdminAccount(id));
	}

	@ResponseBody
	@PostMapping("/approve-register/{id}")
	public boolean approveRegister(@PathVariable Long id) {
		adminAccountService.approve(id);
		return true;
	}

	@GetMapping("/forgot-password")
	public String forgotPassword(){
		return "main/admin-account/forgot-password";
	}

	@ResponseBody
	@PostMapping("/forgot-password")
	public boolean checkEmailExist(@RequestParam String email){
		return !adminAccountService.checkEmailDuplicate(email);
	}

	@ResponseBody
	@PostMapping("/new-password")
	public boolean newPassword(String email,
		@RequestParam String previousPassword, @RequestParam String newPassword){

		return adminAccountService.newPassword(email, previousPassword, newPassword);
	}


	@ResponseBody
	@PostMapping("/send-verification-code")
	public boolean sendVerificationCode(@RequestParam String email){
		String verificationCode = emailService.sendVerificationCode(email);
		emailService.saveVerificationCode(email, verificationCode);
		return true;
	}

	@ResponseBody
	@PostMapping("/verify-verification-code")
	public boolean verifyCode(@RequestParam String email, @RequestParam String verificationCode){
		return emailService.verifyCode(email, verificationCode);
	}


	@ResponseBody
	@PostMapping("/check-email")
	public boolean checkEmailDuplicate(@RequestParam String email) {
		return adminAccountService.checkEmailDuplicate(email);
	}

	@ResponseBody
	@PostMapping("/check-password")
	public boolean checkPasswordMatch(@RequestParam String password, @AuthenticationPrincipal AdminAccountPrincipal adminAccountPrincipal ) {
		return adminAccountService.checkPasswordMatch(password, adminAccountPrincipal);
	}

}


