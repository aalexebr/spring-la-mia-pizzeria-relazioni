package org.java.spring.DTO;

import java.time.LocalDate;

import org.java.spring.pojo.Pizza;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SpecialOfferDTO {
	
	@NotBlank(message = "Name is mandatory")
	@NotNull(message = "Name cannot be null")
	@NotEmpty(message = "Name cannot be null")
	private String title;
	
	@NotNull(message = "date cannot be null")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate startDate;
	
	@NotNull(message = "date cannot be null")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate endDate;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
	

}
