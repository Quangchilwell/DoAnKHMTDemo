package com.DoAnKHMT.restaurantRoom.Model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InvoiceDTO {
	private int id;
	
	@NotEmpty
	@NotNull
	private String personName;
	
	@NotEmpty
	@NotNull
	private String phone;
	
	@NotNull
	private int numberPeople;
	private int quantityRooms;
	@NotNull
	private String startDate;
	@NotNull
	private String endDate;
	private String invoiceCreationDate;
	private String invoiceCompletionDate;
	
	private float deposit;
	private float servicePrice;
	private float roomPrice;
	private float totalPrice;
	
	private String description;
	

}
