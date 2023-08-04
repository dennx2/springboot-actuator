package com.humber.j2ee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.humber.j2ee.model.Category;
import com.humber.j2ee.model.Cuisine;
import com.humber.j2ee.model.Dish;
import com.humber.j2ee.repository.CategoryRepository;
import com.humber.j2ee.repository.DishRepository;

@SpringBootApplication
public class SpringActuator implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private DishRepository dishRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringActuator.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		categoryRepository.save(Category.builder()
				.name("Vegan")
				.color("green")
				.build());
		
		categoryRepository.save(Category.builder()
				.name("Dariy")
				.color("cyan")
				.build());
		
		categoryRepository.save(Category.builder()
				.name("Chicken")
				.color("orange")
				.build());
		
		dishRepository.save(Dish.builder()
				.name("Poutine")
				.cuisine(Cuisine.builder().cuisineName("Canadian").country("Canadian").build())
				.description("Fries with gravy")
				.category(Category.builder().name("Seafood").color("blue").build())
				.price(19.99)
				.build());
		
	}
}
