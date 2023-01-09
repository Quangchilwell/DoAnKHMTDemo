package com.DoAnKHMT.restaurantRoom.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the introdution database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Introdution.findAll", query="SELECT i FROM Introdution i")
public class Introdution implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String address;

	@Lob
	private String email;

	@Lob
	private String name;

	@Lob
	private String phone;

	
}