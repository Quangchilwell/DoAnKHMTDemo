package com.DoAnKHMT.restaurantRoom.Model;

import lombok.Data;

@Data
public class CancelRequestDTO {
	private int id;
	private String cancelDate;
	private String customerName;
	private String phone;
	private float deposit;
	private String description; 
}
