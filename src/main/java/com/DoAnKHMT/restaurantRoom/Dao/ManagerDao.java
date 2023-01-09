package com.DoAnKHMT.restaurantRoom.Dao;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Entity.Manager;

public interface ManagerDao {
	public List<Manager> getAll();
	
	public void add(Manager manager);
	
	public void update(Manager manager);
	
	public void delete(Manager manager);
	
	public Manager getByID(int id);
	
	public Manager getByUsername(String username);
}
