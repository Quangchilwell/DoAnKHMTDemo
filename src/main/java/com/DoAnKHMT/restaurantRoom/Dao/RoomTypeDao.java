package com.DoAnKHMT.restaurantRoom.Dao;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Entity.RoomType;

public interface RoomTypeDao {
	public void addRoomType(RoomType roomType);
	
	public void updateRoomType(RoomType roomType);
	
	public void deleteRoomType(RoomType roomType);
	
	public List<RoomType> getAll();
	
	public RoomType getRoomTypeByID(int id);
	
}
