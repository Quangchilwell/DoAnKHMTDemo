package com.DoAnKHMT.restaurantRoom.Service;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Model.RoomTypeDTO;

public interface RoomTypeService {
	public void addRoomType(RoomTypeDTO roomTypeDTO);

	public void updateRoomType(RoomTypeDTO roomTypeDTO);

	public void deleteRoomType(int id);

	public List<RoomTypeDTO> getAll();

	public RoomTypeDTO getRoomTypeByID(int id);
}
