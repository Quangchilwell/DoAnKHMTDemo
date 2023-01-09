package com.DoAnKHMT.restaurantRoom.Service;

import com.DoAnKHMT.restaurantRoom.Model.IntrodutionDTO;

public interface IntrodutionService {
	public IntrodutionDTO getIntro(int id);
	
	public void update(IntrodutionDTO introdutionDTO);
}
