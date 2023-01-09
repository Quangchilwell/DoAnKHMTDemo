package com.DoAnKHMT.restaurantRoom.Model;

import lombok.Data;

@Data
public class UseRoomSeviceDTO {
	private int id;
	
	private int idService;
	private ServiceInRoomDTO serviceInRoomDTO;
	
	private int idInvoice;
	private InvoiceDTO invoiceDTO;
	
	private int idRoom;
	private RoomDTO roomDTO;
	
	private int quantity;
	private float unitPrice;
	private String description;
	
}
