package com.DoAnKHMT.restaurantRoom.Service;

import java.sql.Timestamp;
import java.util.List;

import com.DoAnKHMT.restaurantRoom.Model.RoomDTO;

public interface RoomService {
	public List<RoomDTO> getAllRoom();
	
	public List<RoomDTO> getByRoomType(int idType);
	
	public List<RoomDTO> getByHousing(int idHousing);
	
	public List<RoomDTO> getRoomsAvailableForOrder(int idOrder, Timestamp startDate, Timestamp endDate); 
	
	public List<RoomDTO> getRoomsAvailableInInvoice(Timestamp startDate, Timestamp endDate); 
	
	public void addRoom(RoomDTO roomDTO);
	
	public void updateRoom(RoomDTO roomDTO);
	
	public void deleteRoom(int id);
	
	public RoomDTO getRoomByID(int id);
	
	public List<RoomDTO> getRoomByStatus(String status);
}
