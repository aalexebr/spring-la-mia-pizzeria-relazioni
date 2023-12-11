package org.java.spring;

import java.time.LocalDate;

import org.java.spring.pojo.Ingredient;
import org.java.spring.pojo.Pizza;
import org.java.spring.pojo.SpecialOffer;
import org.java.spring.service.IngredientService;
import org.java.spring.service.PizzaService;
import org.java.spring.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaRelazioni1Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaRelazioni1Application.class, args);
	}
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialOfferService specialOrderService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Override
	public void run(String... args) throws Exception {
		
		Ingredient a = new Ingredient("flour");
		Ingredient b = new Ingredient("olive oil");
		Ingredient c = new Ingredient("salt");
		Ingredient e = new Ingredient("garlic");
		ingredientService.save(a);
		ingredientService.save(b);
		ingredientService.save(c);
		ingredientService.save(e);
		
		Pizza p = new Pizza("pizza5",8.99,"descr1","https://cdn.drawception.com/drawings/709036/HX97OgbklV.png");
		
		pizzaService.save(new Pizza("pizza1",5.99,"descr1","https://cdn.drawception.com/drawings/709036/HX97OgbklV.png",a,b));
		pizzaService.save(new Pizza("pizza2",6.99,"descr1","https://cdn.drawception.com/drawings/709036/HX97OgbklV.png",e));
		pizzaService.save(new Pizza("pizza3",7.99,"descr1","https://cdn.drawception.com/drawings/709036/HX97OgbklV.png"));
		pizzaService.save(new Pizza("pizza4",8.99,"descr1","https://cdn.drawception.com/drawings/709036/HX97OgbklV.png"));
		pizzaService.save(p);
		
		specialOrderService.save(new SpecialOffer(LocalDate.now(),LocalDate.now().plusDays(7),"promotion111",p));
		specialOrderService.save(new SpecialOffer(LocalDate.now(),LocalDate.now().plusDays(7),"promotion222",p));
	}

}
