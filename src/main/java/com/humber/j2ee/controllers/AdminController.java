package com.humber.j2ee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.humber.j2ee.model.Category;
import com.humber.j2ee.model.Dish;
import com.humber.j2ee.repository.CategoryRepository;
import com.humber.j2ee.services.DishService;

@Controller
@RequestMapping(value = "/restaurant/admin")
public class AdminController {
	
	@Value("${restaurant.name}")
	public String name;
	
	@Autowired
	private DishService dishService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/")
	public String admin(Model model) {
		model.addAttribute("restaurantName", name);
		return "admin/admin";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("restaurantName", name);
		model.addAttribute("item", new Dish());
		System.out.println(categoryRepository.findAll()); // TEST
		model.addAttribute("categories", categoryRepository.findAll());
		return "admin/add-item";
	}
	
	@PostMapping("/add")
	public String addItem(Model model, @ModelAttribute Dish dish) {
		Category category = categoryRepository.findById(dish.getCategory().getId()).orElse(null);
		dish.setCategory(category);
		dishService.addDish(dish);
		
		System.out.println(dish.getCategory()); // TEST
		return "redirect:/restaurant/menu?success=Item Added Successfully";
		// return "menu"; // This is incorrect, the url will be off and many other issues
	}
	
	
//	@PostMapping("/add")
//	public String addItem(Model model, @ModelAttribute Dish dish) {
//		dishService.addDish(dish);
//		return "redirect:/restaurant/menu?success=Item Added Successfully";
//		// return "menu"; // This is incorrect, the url will be off and many other issues
//	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteItem(Model model, @PathVariable int id) {
		dishService.deleteDish(id);
		return "redirect:/restaurant/menu?success=Item Deleted Successfully";
	}
	
	
	
	
	
	
}
