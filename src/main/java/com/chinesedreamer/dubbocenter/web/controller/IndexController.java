package com.chinesedreamer.dubbocenter.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request){
		return "mgmt/list";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index2(Model model, HttpServletRequest request){
		return "mgmt/list";
	}
}
