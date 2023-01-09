package com.DoAnKHMT.restaurantRoom.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the use_room_service database table.
 * 
 */
@Entity
@Data
@Table(name="use_room_service")
@NamedQuery(name="UseRoomService.findAll", query="SELECT u FROM UseRoomService u")
public class UseRoomService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="id_invoice")
	private int idInvoice;

	@Column(name="id_service_in_room")
	private int idServiceInRoom;
	
	@Column(name="id_room")
	private int idRoom;

	private int quantity;

	@Column(name="unit_price")
	private float unitPrice;
	
	@Lob
	private String description;
}