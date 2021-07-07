package com.te.springmvc2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReqestDeligationController {
	
	@GetMapping("/redirect")
	public String redirectReqest() {
		return "redirect:http://google.com";
	}
	
	@RequestMapping("/index-page")
	public String index() {
		return "homepage";
	}
	
	@GetMapping("/forward")
	public String forwardReqest() {
		return "forward:/index-page";
	}

}
