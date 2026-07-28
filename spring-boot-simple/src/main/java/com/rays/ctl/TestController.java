package com.rays.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "Test")
public class TestController {

	@GetMapping
	public String display() {
		return "This is a display method";
	}

	@PostMapping
	public String submit() {
		return "This is a submit method";
	}

	@GetMapping("get")
	public String get() {
		return "This is a get endpoint";
	}

	@PostMapping("save")
	public String save() {
		return "This is a post endpoint";
	}

}