package com.beastytech.springboot.hello;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@RestController
public class HelloController {
	
	@RequestMapping("hello")
	public String sayHi() {
		System.out.println("hello!");
		return "hi";
	}
	
//*
	@RequestMapping("hello/{name}")
	public String sayHi(@PathVariable Map<String, String> pathVars, Model model) {
		return "hi " + pathVars.get("name");
	}
//	*/
}
