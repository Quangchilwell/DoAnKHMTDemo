package com.DoAnKHMT.restaurantRoom.Dao;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Entity.Housing;

public interface HousingDao {
	public List<Housing> getAll();
	
	public void addHousing(Housing housing);
	
	public void updateHousing(Housing housing);
	
	public void deleteHousing(Housing housing);
	
	public Housing getHousingByID(int id);
}
