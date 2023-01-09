package com.DoAnKHMT.restaurantRoom.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;


/**
 * The persistent class for the cancel_request database table.
 * 
 */
@Entity
@Data
@Table(name="cancel_request")
@NamedQuery(name="CancelRequest.findAll", query="SELECT c FROM CancelRequest c")
public class CancelRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="cancel_date")
	private Timestamp cancelDate;

	@Lob
	@Column(name="customer_name")
	private String customerName;

	private float deposit;

	@Lob
	private String description;

	@Lob
	private String phone;

}