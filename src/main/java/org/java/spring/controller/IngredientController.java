package org.java.spring.controller;

import java.util.List;

import org.java.spring.pojo.Ingredient;
import org.java.spring.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping("/ingredients")
	public String ingedientsAll(Model model) {
		
		List <Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("ingredients", ingredients);
		
		return("ingredients");
	}
	
	@GetMapping("/create-ingredient")
	public String createIngredient(Model model) {
		
		Ingredient ingredient = new Ingredient();
		model.addAttribute("ingredient", ingredient);
		return "ingredient-form";
	}
	
	@PostMapping("/create-ingredient")
	public String storeIngredient(Model model,
		@Valid @ModelAttribute Ingredient ingredient,
		BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return "ingredient-form";
		}
		
		ingredientService.save(ingredient);
			
		return("redirect:/ingredients");
	}
		

}
