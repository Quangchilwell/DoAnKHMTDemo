package com.DoAnKHMT.restaurantRoom.Service;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Model.HousingDTO;

public interface HousingService {
	public List<HousingDTO> getAll();
	
	public void addHousing(HousingDTO housingDTO);
	
	public void updateHousing(HousingDTO housingDTO);
	
	public void deleteHousing(int id);
	
	public HousingDTO getHousingByID(int id);
}
