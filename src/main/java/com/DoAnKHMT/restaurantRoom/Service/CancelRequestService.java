package com.DoAnKHMT.restaurantRoom.Service;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Model.CancelRequestDTO;

public interface CancelRequestService {
	public List<CancelRequestDTO> getAll();
	
	public void add(CancelRequestDTO cancelRequestDTO);
	
	public void update(CancelRequestDTO cancelRequestDTO);
	
	public void delete(int id);
	
	public CancelRequestDTO getByID(int id);
}
