package com.DoAnKHMT.restaurantRoom.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name="room_type")
@NamedQuery(name="RoomType.findAll", query="SELECT r FROM RoomType r")
public class RoomType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String img;

	@Lob
	private String name;
	
	@Column(name="price")
	private float price;
	
	@Column(name="description")
	private String description;
}