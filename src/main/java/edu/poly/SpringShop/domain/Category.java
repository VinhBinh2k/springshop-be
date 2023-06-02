package edu.poly.SpringShop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;
	
	
	@Column(name="name", nullable = false, length = 100)
	private String name;
	
	@Enumerated
	@Column(name="status", nullable = false)
	private CategoryStatus status;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
