package com.DoAnKHMT.restaurantRoom.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the service_in_room database table.
 * 
 */
@Entity
@Table(name="service_in_room")
@Data
@NamedQuery(name="ServiceInRoom.findAll", query="SELECT s FROM ServiceInRoom s")
public class ServiceInRoom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(name="price")
	private float price;

	@Lob
	private String description;

	@Lob
	private String name;
	
	@Lob
	private String unit;
}