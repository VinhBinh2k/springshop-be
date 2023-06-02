package edu.poly.SpringShop.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import edu.poly.SpringShop.domain.CategoryStatus;
import lombok.Data;

@Data
public class CategoryDto implements Serializable {

	private  Long id;
	
	
	@NotEmpty(message = "Category name is required")
	private  String name;
	
	
	private  CategoryStatus status;
}
