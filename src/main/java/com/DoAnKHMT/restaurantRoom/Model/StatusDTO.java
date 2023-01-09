package com.DoAnKHMT.restaurantRoom.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StatusDTO {
	private int id;
	
	@NotEmpty
	@NotNull
	private String name;
	private String description;
	private String createAt;
}
