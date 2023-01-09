package com.DoAnKHMT.restaurantRoom.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="order_room")
@Data
@NamedQuery(name="OrderRoom.findAll", query="SELECT o FROM OrderRoom o")
public class OrderRoom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private float deposit;

	@Column(name="number_people")
	private int numberPeople;

	@Column(name="status")
	private int status;
	
	@Column(name="order_date")
	private Timestamp orderDate;

	@Lob
	@Column(name="person_name")
	private String personName;

	@Lob
	private String phone;
	
	@Lob
	private String description;

	@Column(name="start_date")
	private Timestamp startDate;
	
	@Column(name="end_date")
	private Timestamp endDate;

}