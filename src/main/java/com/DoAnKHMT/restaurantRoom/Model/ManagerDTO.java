package com.DoAnKHMT.restaurantRoom.Model;

import lombok.Data;

@Data
public class ManagerDTO {
	private int id;
	
	private String username;
	private String password;
	private String role;
	private String description;
}
