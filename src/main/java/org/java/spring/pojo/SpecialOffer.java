package org.java.spring.pojo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SpecialOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate startDate;
	
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate endDate;
	
	@Column(nullable = false, length = 32)
	private String title;
	
	@ManyToOne
	private Pizza pizza;
	
	public SpecialOffer() {
		
	}
	
	public SpecialOffer(LocalDate startDate, LocalDate endDate, String title, Pizza pizza) {
		setEndDate(endDate);
		setStartDate(startDate);
		setTitle(title);
		setPizza(pizza);
			
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	
	@Override
	public String toString() {
		return getTitle()+"  [from: "+getStartDate()+" until: "+getEndDate()+"]";
	}

}
