package com.DoAnKHMT.restaurantRoom.Service;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Model.RoomBusyDTO;

public interface RoomBusyService {
	public List<RoomBusyDTO> getAll();
	
	public List<RoomBusyDTO> getByIdOrder(int idOrder);
	
	public List<RoomBusyDTO> getByIdInvoice(int idInvoice);
	
	public List<RoomBusyDTO> getByIdRoom(int idRoom);
	
	public void add(RoomBusyDTO roomBusyDTO);
	
	public void update(RoomBusyDTO roomBusyDTO);
	
	public void delete(int id);
	
	public RoomBusyDTO getByID(int id);
}
