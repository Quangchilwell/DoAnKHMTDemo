package com.DoAnKHMT.restaurantRoom.Service;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Model.StatusDTO;

public interface StatusService {
	public List<StatusDTO> getAll();
	
	public List<StatusDTO> getAllInBin();

	public void addStatus(StatusDTO statusDTO);

	public void updateStatus(StatusDTO statusDTO);
	
	public void restoreStatus(int id);

	public void deleteStatus(int id);
	
	public void softDelete(int id);

	public StatusDTO getByID(int id);
}
