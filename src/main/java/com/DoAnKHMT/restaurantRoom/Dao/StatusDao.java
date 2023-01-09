package com.DoAnKHMT.restaurantRoom.Dao;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Entity.Status;

public interface StatusDao {
	public List<Status> getAll();
	
	public void addStatus(Status status);
	
	public void updateStatus(Status status);
	
	public void deleteStatus(Status status);
	
	public void softDelete(Status status);
	
	public Status getByID(int id);
	
	public int recordsInBin();
}
