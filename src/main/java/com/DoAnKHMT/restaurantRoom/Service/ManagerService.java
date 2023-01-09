package com.DoAnKHMT.restaurantRoom.Service;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Model.ManagerDTO;

public interface ManagerService {
	public List<ManagerDTO> getAll();
	
	public void add(ManagerDTO managerDTO);
	
	public void update(ManagerDTO managerDTO);
	
	public void delete(int id);
	
	public ManagerDTO getByID(int id);
	
	public ManagerDTO getByUsername(String username);
}
