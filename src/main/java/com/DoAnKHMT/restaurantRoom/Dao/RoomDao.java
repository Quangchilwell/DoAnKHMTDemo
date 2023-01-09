package com.DoAnKHMT.restaurantRoom.Dao;

import java.sql.Timestamp;
import java.util.List;

import com.DoAnKHMT.restaurantRoom.Entity.Room;

public interface RoomDao {
	public List<Room> getAll();
	
	public List<Room> getByRoomType(int idType);
	
	public List<Room> getByHousing(int idHousing);

	public List<Room> getRoomsAvailable(int idOrder);

	public List<Room> getRoomsAvailableInInvoice(Timestamp startDate, Timestamp endDate);

	public void addRoom(Room room);

	public void updateRoom(Room room);

	public void deleteRoom(Room room);

	public Room getRoomByID(int id);
}
