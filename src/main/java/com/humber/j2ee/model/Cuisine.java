package com.humber.j2ee.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder 
@Embeddable
public class Cuisine {

//	private int id;   // You dont need id because it will be in the same table as Dish
	private String cuisineName;
	private String country; 
	
}
