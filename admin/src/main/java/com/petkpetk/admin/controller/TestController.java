package com.petkpetk.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {



	@GetMapping("/user-management")
	public String tables(){
		return "management/user-management";
	}

	@GetMapping("/buttons")
	public String buttons(){
		return "buttons";
	}

	@GetMapping("/cards")
	public String cards(){
		return "cards";
	}
	@GetMapping("/utilities-color.html")
	public String utilitiesColor(){
		return "utilities-color";
	}

	@GetMapping("/utilities-border")
	public String utilitiesBorder(){
		return "utilities-border";
	}


	@GetMapping("/blank")
	public String blank(){
		return "blank";
	}

	@GetMapping("/charts")
	public String charts(){
		return "charts";
	}

}
