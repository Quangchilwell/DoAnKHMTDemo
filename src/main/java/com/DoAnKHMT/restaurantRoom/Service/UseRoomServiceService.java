package com.DoAnKHMT.restaurantRoom.Service;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Model.UseRoomSeviceDTO;

public interface UseRoomServiceService {
	public List<UseRoomSeviceDTO> getAll();
	
	public List<UseRoomSeviceDTO> getByIdService(int idService);
	
	public void add(UseRoomSeviceDTO useRoomServiceDto);
	
	public void update(UseRoomSeviceDTO useRoomServiceDto);
	
	public void delete(int id);
	
	public UseRoomSeviceDTO getByID(int id);
	
	public List<UseRoomSeviceDTO> getByIdInvoice(int idInvoice);
}
