package edu.poly.SpringShop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)//trạng thái trả về cho client
public class CategoryException extends RuntimeException  {

	public CategoryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}



	
}
