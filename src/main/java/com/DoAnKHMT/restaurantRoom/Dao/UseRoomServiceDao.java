package com.DoAnKHMT.restaurantRoom.Dao;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Entity.UseRoomService;

public interface UseRoomServiceDao {
	public List<UseRoomService> getAll();
	
	public List<UseRoomService> getByIdService(int idService);
	
	public void add(UseRoomService useRoomService);
	
	public void update(UseRoomService useRoomService);
	
	public void delete(UseRoomService useRoomService);
	
	public UseRoomService getByID(int id);
	
	public List<UseRoomService> getByIdInvoice(int idInvoice);
	
}
