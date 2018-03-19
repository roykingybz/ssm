package com.gucheng.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class TestHello {
	
	@RequestMapping(value = "/world",method = RequestMethod.GET)
	public String hello(){
		return "test";
	}

}
