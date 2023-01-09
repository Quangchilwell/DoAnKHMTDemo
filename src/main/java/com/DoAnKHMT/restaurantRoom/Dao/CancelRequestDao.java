package com.DoAnKHMT.restaurantRoom.Dao;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Entity.CancelRequest;

public interface CancelRequestDao {
	public List<CancelRequest> getAll();
	
	public void add(CancelRequest cancelRequest);
	
	public void update(CancelRequest cancelRequest);
	
	public void delete(CancelRequest cancelRequest);
	
	public CancelRequest getByID(int id);
}
