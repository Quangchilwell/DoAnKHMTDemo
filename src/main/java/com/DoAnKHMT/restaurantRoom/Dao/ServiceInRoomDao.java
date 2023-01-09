package com.DoAnKHMT.restaurantRoom.Dao;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Entity.ServiceInRoom;

public interface ServiceInRoomDao {
	public List<ServiceInRoom> getAll();
	
	public void addServiceInRoom(ServiceInRoom serviceInRoom);
	
	public void updateServiceInRoom(ServiceInRoom serviceInRoom);
	
	public void deleteServiceInRoom(ServiceInRoom serviceInRoom);
	
	public ServiceInRoom getByID(int id);
	
	public List<ServiceInRoom> getByNameLike(String name);
}
