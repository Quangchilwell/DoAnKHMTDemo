package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.HousingDao;
import com.DoAnKHMT.restaurantRoom.Entity.Housing;
import com.DoAnKHMT.restaurantRoom.Model.HousingDTO;
import com.DoAnKHMT.restaurantRoom.Service.HousingService;

@Service
public class HousingServiceImpl implements HousingService{

	@Autowired
	HousingDao housingDao;
	
	@Override
	public List<HousingDTO> getAll() {
		List<HousingDTO> housingDTOs = new ArrayList<HousingDTO>();
		List<Housing> housings = housingDao.getAll();
		
		for(Housing housing: housings)
		{
			HousingDTO housingDTO = new HousingDTO();
			housingDTO.setId(housing.getId());
			housingDTO.setName(housing.getName());
			housingDTO.setFloorsQuantity(housing.getFloorsQuantity());
			housingDTO.setRoomsQuantity(housing.getRoomsQuantity());
			housingDTO.setDescription(housing.getDescription());
			housingDTOs.add(housingDTO);
		}
		
		return housingDTOs;
	}

	@Override
	public void addHousing(HousingDTO housingDTO) {
		Housing housing = new Housing();
		housing.setId(housingDTO.getId());
		housing.setName(housingDTO.getName());
		housing.setFloorsQuantity(housingDTO.getFloorsQuantity());
		housing.setRoomsQuantity(housingDTO.getRoomsQuantity());
		housing.setDescription(housingDTO.getDescription());
		housingDao.addHousing(housing);
	}

	@Override
	public void updateHousing(HousingDTO housingDTO) {
		Housing housing = housingDao.getHousingByID(housingDTO.getId());
		if(housing != null) {
			housing.setId(housingDTO.getId());
			housing.setName(housingDTO.getName());
			housing.setFloorsQuantity(housingDTO.getFloorsQuantity());
			housing.setRoomsQuantity(housingDTO.getRoomsQuantity());
			housing.setDescription(housingDTO.getDescription());
			housingDao.updateHousing(housing);
		}
	}

	@Override
	public void deleteHousing(int id) {
		Housing housing = housingDao.getHousingByID(id);
		if(housing != null) {
			housingDao.deleteHousing(housing);			
		}
		
	}

	@Override
	public HousingDTO getHousingByID(int id) {
		Housing housing = housingDao.getHousingByID(id);
		if(housing != null) {
			HousingDTO housingDTO = new HousingDTO();
			housingDTO.setId(housing.getId());
			housingDTO.setName(housing.getName());
			housingDTO.setFloorsQuantity(housing.getFloorsQuantity());
			housingDTO.setRoomsQuantity(housing.getRoomsQuantity());
			housingDTO.setDescription(housing.getDescription());
			return housingDTO;
		}
		return null;
	}

}
