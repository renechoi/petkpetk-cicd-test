package com.petkpetk.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/test")
	public String home() {
		return "/index2";
	}

	@GetMapping("/test/error")
	public String error() {
		new RuntimeException();
		return "";
	}
}
