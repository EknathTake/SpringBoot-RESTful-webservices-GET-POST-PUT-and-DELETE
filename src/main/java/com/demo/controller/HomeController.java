package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private Logger log = LoggerFactory.getLogger(HomeController.class);

	@GetMapping(path="/a")
	public String home(Model model) {
        model.addAttribute("name", "Eknath Take");
		log.info("ddddddddddddddddddddddddd");
		return "index";
	}
}
