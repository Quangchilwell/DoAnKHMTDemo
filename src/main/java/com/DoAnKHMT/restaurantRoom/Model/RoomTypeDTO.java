package com.DoAnKHMT.restaurantRoom.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RoomTypeDTO {
	private int id;
	
	@NotEmpty
	@NotNull
	private String name;
	private String image;
	
	@NotNull
	private float price;
	private String description;
	private MultipartFile file;
}
