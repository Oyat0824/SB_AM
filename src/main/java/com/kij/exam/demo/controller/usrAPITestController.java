package com.kij.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class usrAPITestController {
	@RequestMapping("/usr/home/APITest")
	public String showAPITest() {
		return "usr/home/APITest";
	}
	
	@RequestMapping("/usr/home/APITest2")
	public String showAPITest2() {
		return "usr/home/APITest2";
	}
	
	@RequestMapping("/usr/home/APITest3")
	public String showAPITest3() {
		return "usr/home/APITest3";
	}
}
