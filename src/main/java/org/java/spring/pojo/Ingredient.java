package org.java.spring.pojo;

import java.util.List;

import org.hibernate.validator.constraints.Length;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true, length = 32)
	@NotBlank(message = "Name is mandatory")
	@NotNull(message = "Name cannot be null")
	@NotEmpty(message = "Name cannot be null")
	@Length(max = 32, message = "name must be max 32 chars")
	private String name;
	
	@ManyToMany(mappedBy="ingredients")
	private List<Pizza> pizzas;
	
	public Ingredient() {}
	
	public Ingredient(String name) {
		setName(name);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	public void clearPizzas() {
		for(Pizza pizza : getPizzas()) {
			pizza.clearIngredients(this);
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
	

}
