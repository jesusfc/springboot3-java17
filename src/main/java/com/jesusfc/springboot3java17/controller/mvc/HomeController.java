package com.jesusfc.springboot3java17.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String homeMVC() {
		return "forward:/mvc/";
	}
	@GetMapping("/rest")
	public String homeREST() {
		return "forward:/rest/user/list";
	}
}
