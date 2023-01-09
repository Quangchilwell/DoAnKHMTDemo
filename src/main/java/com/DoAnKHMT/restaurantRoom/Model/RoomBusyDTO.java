package com.DoAnKHMT.restaurantRoom.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RoomBusyDTO {
	private int id;
	
	private int idOrder;
	private OrderRoomDTO orderRoomDTO;
	
	private int idInvoice;
	private InvoiceDTO invoiceDTO;
	
	private int idRoom; 
	private RoomDTO roomDTO;
	
	private int idStatus;
	private StatusDTO statusDTO;
	
	@NotEmpty
	@NotNull
	private String startDate;
	
	@NotEmpty
	@NotNull
	private String endDate;

	@NotNull
	private float daysBooked;
	private float unitPrice;
	private String description;
}
