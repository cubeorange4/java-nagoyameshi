package com.example.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.nagoyameshi.service.StripeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MembershipController {
	
	private final StripeService stripeService;
	
	public MembershipController(StripeService stripeService) {
		this.stripeService = stripeService;
	}
	
	@GetMapping("/membership")
	public String index(HttpServletRequest httpServletRequest, Model model) {
		String sessionId = stripeService.createStripeSession(httpServletRequest);
		
		model.addAttribute("sessionId", sessionId);
		
		return "membership/index";
	}
}
