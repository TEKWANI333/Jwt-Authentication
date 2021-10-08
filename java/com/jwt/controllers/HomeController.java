package com.jwt.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@RequestMapping("/welcome")
	public String welcome() {
		String text ="this is private home page";
		text+= " this page is not allowed for unauthenticated users";
		return text;
	}
	
	@RequestMapping("/userdata")
	public String dataFromUser() {
		String data ="Data from users";
		return data;
	}

}
