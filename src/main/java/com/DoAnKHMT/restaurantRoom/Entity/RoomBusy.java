package com.DoAnKHMT.restaurantRoom.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import lombok.Data;

import java.util.Date;

/**
 * The persistent class for the room_busy database table.
 * 
 */
@Entity
@Data
@Table(name = "room_busy")
@NamedQuery(name = "RoomBusy.findAll", query = "SELECT r FROM RoomBusy r")
public class RoomBusy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	@Column(name = "id_room")
	private int idRoom;

	@Column(name = "id_status")
	private int idStatus;

	@Column(name = "id_order")
	private int idOrder;

	@Column(name = "id_invoice")
	private int idInvoice;

	@Column(name = "days_booked")
	private float daysBooked;

	@Column(name = "unit_price")
	private float unitPrice;

	@Column(name = "start_date")
	private Timestamp startDate;

	@Column(name = "end_date")
	private Timestamp endDate;

}