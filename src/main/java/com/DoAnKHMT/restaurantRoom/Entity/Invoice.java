package com.DoAnKHMT.restaurantRoom.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Invoice.findAll", query="SELECT i FROM Invoice i")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private float deposit;

	private String description;

	@Column(name="invoice_completion_date")
	private Timestamp invoiceCompletionDate;

	@Column(name="invoice_creation_date")
	private Timestamp invoiceCreationDate;

	@Column(name="number_people")
	private int numberPeople;

	@Lob
	@Column(name="person_name")
	private String personName;

	@Lob
	private String phone;

	@Column(name="quantity_rooms")
	private int quantityRooms;

	@Column(name="soft_delete")
	private int softDelete;

	@Column(name="start_date")
	private Timestamp startDate;
	
	@Column(name="end_date")
	private Timestamp endDate;

	@Column(name = "room_price")
	private float roomPrice;
	
	@Column(name = "service_price")
	private float servicePrice;
	
	@Column(name = "total_price")
	private float totalPrice;
}