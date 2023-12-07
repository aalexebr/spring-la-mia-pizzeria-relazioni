package org.java.spring.controller;


import java.util.List;

import org.java.spring.DTO.SpecialOfferDTO;
import org.java.spring.pojo.Pizza;
import org.java.spring.pojo.SpecialOffer;
import org.java.spring.service.PizzaService;
import org.java.spring.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class MainController {
	
	@Autowired
	PizzaService pizzaService;
	
	@Autowired
	SpecialOfferService spOffService;
	
	@GetMapping("/")
	public String home(Model model,
			@RequestParam(required = false) String query) {
		
		List<Pizza> pizzaList = query != null ? pizzaService.findByName(query) : pizzaService.findAll();
//		List<Pizza> pizzaList = pizzaService.findByName("1");			
		model.addAttribute("pizzas", pizzaList);
		model.addAttribute("query", query);
		return("home");
	}
	
	@GetMapping("/pizza/{id}")
	public String singlePizza(Model model, @PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza != null ? pizza : "none found");
		return("pizza-show");
	}
	
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		Pizza newPizza = new Pizza();
		model.addAttribute("pizza", newPizza);
		return("pizza-create");
	}
	
	@PostMapping("/pizza/create")
	public String storePizza(Model model,
			@Valid @ModelAttribute Pizza pizza, 
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			System.out.println(bindingResult);
			model.addAttribute("pizza", pizza);
			return "pizza-create";
		}
		
		try {
			
			pizzaService.save(pizza);
		} catch(Exception e) {
			
			bindingResult.addError(new FieldError("pizza", "name", pizza.getName(), false, null, null, "Name must be unique"));
			model.addAttribute("pizza", pizza);
			return "pizza-create";
		}
		
//		pizzaService.save(pizza);
		
		return("redirect:/");
	}
	
	@GetMapping("/pizza/edit/{id}")
	public String editPizza(Model model,
			@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		System.out.println(pizza);	
		return("update");
	}
	
	@PostMapping("/pizza/edit/{id}")
	public String updatePizza(Model model,
			@Valid @ModelAttribute Pizza pizza, 
			BindingResult bindingResult){
//	System.out.println(pizza);	

		if (bindingResult.hasErrors()) {
			
//			System.out.println(bindingResult);
			model.addAttribute("pizza", pizza);
			return "update";
		}
		
		try {
			
			pizzaService.save(pizza);
		} catch(Exception e) {
			
			bindingResult.addError(new FieldError("pizza", "name", pizza.getName(), false, null, null, "Name must be unique"));
			model.addAttribute("pizza", pizza);
			return "update";
		}

		return("redirect:/");
	}
	
	@PostMapping("/pizza/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		
		pizzaService.delete(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/special-offer/{id}")
	public String addSpecialOffer(@PathVariable int id) {

		return("create-offer");
	}
	
	@PostMapping("/pizza/special-offer/{id}")
	public String storeSpecialOffer(Model model,
			@PathVariable int id,
			@Valid @ModelAttribute SpecialOfferDTO modelDTO,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			model.addAttribute("modelDTO",bindingResult);
			return ("create-offer");
		}
		Pizza p = pizzaService.findById(id);
		
		SpecialOffer x = new SpecialOffer(modelDTO.getStartDate(),
		modelDTO.getEndDate(),modelDTO.getTitle(),p);
		
		spOffService.save(x);

		return ("redirect:/pizza/"+id);
	}
	
	@GetMapping("pizza/update-special-offer/{id}")
	public String editSpOffer(@PathVariable int id,
			Model model) {
		SpecialOffer x = spOffService.findById(id);
		model.addAttribute("offer", x);
		return("update-offer");
	}
	
	@PostMapping("pizza/update-special-offer/{id}")
	public String updateSpOffre(@PathVariable int id,
			Model model,
			@Valid @ModelAttribute SpecialOfferDTO modelDTO, 
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			model.addAttribute("modelDTO",bindingResult);
			return ("update-offer");
		}
		
		SpecialOffer x = spOffService.findById(id);
		System.out.println(x);
		x.setTitle(modelDTO.getTitle());
		x.setStartDate(modelDTO.getStartDate());
		x.setEndDate(modelDTO.getEndDate());
		spOffService.save(x);
		System.out.println(x);
		return "redirect:/";
	}
	

}
