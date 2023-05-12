package com.petkpetk.admin.service.admin;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;
	private final VerificationCodeGenerator verificationCodeGenerator;

	private final RedisTemplate<String, Object> redisTemplate;

	public String sendVerificationCode(String recipientEmail) {
		String verificationCode = verificationCodeGenerator.generateVerificationCode();
		mailSender.send(generateMessage(recipientEmail, verificationCode));
		return verificationCode;

	}

	public void saveVerificationCode(String recipientEmail, String verificationCode) {
		redisTemplate.opsForValue().set(generateRedisKey(recipientEmail), verificationCode);
	}

	public boolean verifyCode(String recipientEmail, String verificationCode) {
		String storedCode = (String) redisTemplate.opsForValue().get(generateRedisKey(recipientEmail));
		return storedCode != null && storedCode.equals(verificationCode);
	}

	private SimpleMailMessage generateMessage(String recipientEmail, String verificationCode) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(recipientEmail);
		message.setSubject("인증코드 안내");
		message.setText("인증번호: " + verificationCode);
		return message;
	}

	private String generateRedisKey(String recipientEmail) {
		return "verificationCode:" + recipientEmail;
	}

}

