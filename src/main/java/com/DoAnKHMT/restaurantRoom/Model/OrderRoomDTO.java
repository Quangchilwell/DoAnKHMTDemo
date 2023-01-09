package com.DoAnKHMT.restaurantRoom.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderRoomDTO {
	private int id;
	
	@NotEmpty
	@NotNull
	private String personName;
	
	@NotNull
	private String phone;
	
	@NotNull
	private int numberPeople;
	private float deposit;
	
	private String orderDate;
	
	@NotNull
	@NotEmpty
	private String startDate;
	
	@NotNull
	@NotEmpty
	private String endDate;
	
	private String description;
	private int status;
}
