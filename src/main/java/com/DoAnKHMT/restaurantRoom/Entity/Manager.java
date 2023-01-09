package com.DoAnKHMT.restaurantRoom.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the manager database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Manager.findAll", query="SELECT m FROM Manager m")
public class Manager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	@Lob
	@Column(name="pass_word")
	private String passWord;

	@Lob
	@Column(name="user_name")
	private String userName;
	
	@Lob
	private String role;


}