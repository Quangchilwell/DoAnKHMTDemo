package com.DoAnKHMT.restaurantRoom.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the room database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int floor;

	@Column(name="id_housing")
	private int idHousing;

	@Column(name="id_type")
	private int idType;

	private String name;

	private String description;
	
	private int status;

}