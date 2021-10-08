package com.jwt.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@RequestMapping("/index")
	public String dashboard() {
		return "example";
	}
}
