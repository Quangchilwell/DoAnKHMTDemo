package com.DoAnKHMT.restaurantRoom.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the housing database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Housing.findAll", query="SELECT h FROM Housing h")
public class Housing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	@Column(name="floors_quantity")
	private int floorsQuantity;

	@Lob
	private String name;

	@Column(name="rooms_quantity")
	private int roomsQuantity;

}