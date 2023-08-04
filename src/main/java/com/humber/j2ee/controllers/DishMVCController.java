package com.humber.j2ee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.humber.j2ee.services.DishService;

@Controller
public class DishMVCController {

	@Value("${restaurant.name}")
	public String name;
	
	@Autowired
	private DishService dishService;
	
	@GetMapping("/restaurant")
	public String home(Model model) {
		model.addAttribute("restaurantName", name);
		return "home";
	}
	
	@GetMapping("/restaurant/menu")
	public String menu(Model model, @RequestParam(required = false) String success) {
		model.addAttribute("restaurantName", name);
		model.addAttribute("success", success);
		model.addAttribute("dishes", dishService.getDishes());
		return "menu";
	}
}
