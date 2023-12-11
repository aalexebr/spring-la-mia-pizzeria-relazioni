package org.java.spring.controller;

import org.java.spring.DTO.SpecialOfferDTO;
import org.java.spring.pojo.Pizza;
import org.java.spring.pojo.SpecialOffer;
import org.java.spring.service.IngredientService;
import org.java.spring.service.PizzaService;
import org.java.spring.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class SpecialOfferController {
	
	@Autowired
	PizzaService pizzaService;
	
	@Autowired
	SpecialOfferService spOffService;
	
	@Autowired
	IngredientService ingService;
	
	
	@GetMapping("/pizza/{id}/special-offer")
	public String addSpecialOffer(@PathVariable int id, Model model) {
//		SpecialOfferDTO modelDTO = new SpecialOfferDTO();
//		model.addAttribute("modelDTO", modelDTO);
		return("create-offer");
	}
	
	@PostMapping("/pizza/{id}/special-offer")
	public String storeSpecialOffer(Model model,
			@PathVariable int id,
			@Valid @ModelAttribute SpecialOfferDTO modelDTO,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			model.addAttribute("modelDTO",modelDTO);
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
			SpecialOffer x = spOffService.findById(id);
			model.addAttribute("offer", x);
//			model.addAttribute("modelDTO",bindingResult);
			return ("update-offer");
		}
		
		SpecialOffer x = spOffService.findById(id);
		
		x.setTitle(modelDTO.getTitle());
		x.setStartDate(modelDTO.getStartDate());
		x.setEndDate(modelDTO.getEndDate());
		spOffService.save(x);
		
		int n = x.getPizza().getId();
		return ("redirect:/pizza/"+n);
	}
}
