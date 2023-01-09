package com.DoAnKHMT.restaurantRoom.Service;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Model.ServiceInRoomDTO;

public interface ServiceInRoomService {
	public List<ServiceInRoomDTO> getAll();
	
	public void addServiceInRoom(ServiceInRoomDTO serviceInRoomDTO);
	
	public void updateServiceInRoom(ServiceInRoomDTO serviceInRoomDTO);
	
	public void deleteServiceInRoom(int id);
	
	public ServiceInRoomDTO getByID(int id);
	
	public List<ServiceInRoomDTO> getByNameInclude(String info);
}
