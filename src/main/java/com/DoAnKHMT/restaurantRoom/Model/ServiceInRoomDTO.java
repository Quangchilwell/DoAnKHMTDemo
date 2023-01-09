package com.DoAnKHMT.restaurantRoom.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ServiceInRoomDTO {
	private int id;
	
	@NotNull
	private float price;
	
	@NotEmpty
	@NotNull
	private String name;
	private String description;
	
	@NotEmpty
	@NotNull
	private String unit;
}
