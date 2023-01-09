package com.DoAnKHMT.restaurantRoom.Dao;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Entity.RoomBusy;

public interface RoomBusyDao {
	public List<RoomBusy> getAll();
	
	public List<RoomBusy> getByIdOrder(int idOrder);
	
	public List<RoomBusy> getByIdInvoice(int idInvoice);
	
	public List<RoomBusy> getByIdRoom(int idRoom);
	
	public void add(RoomBusy roomBusy);
	
	public void update(RoomBusy roomBusy);
	
	public void delete(RoomBusy roomBusy);
	
	public RoomBusy getByID(int id);
}
