package com.DoAnKHMT.restaurantRoom.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RoomDTO {
	private int id;
	
	@NotEmpty
	@NotNull
	private String name;
	
	private HousingDTO housingDTO;
	private int idHousing;
	
	private RoomTypeDTO roomTypeDTO;
	private int idType;
	
	
	private int status;
	
	private String description;
	
	@NotNull
	private int floor;
}
