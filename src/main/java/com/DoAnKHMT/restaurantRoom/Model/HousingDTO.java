package com.DoAnKHMT.restaurantRoom.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class HousingDTO {
	private int id;
	
	@NotNull
	@NotEmpty
	private String name;
	private int roomsQuantity;

	@NotNull
	private int floorsQuantity;
	private String description;
}
