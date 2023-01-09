package com.DoAnKHMT.restaurantRoom.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.HousingDao;
import com.DoAnKHMT.restaurantRoom.Entity.Housing;
import com.DoAnKHMT.restaurantRoom.Repository.HousingRepository;

@Repository
public class HousingDaoImpl implements HousingDao{

	@Autowired
	HousingRepository housingRepository;
	
	@Override
	public List<Housing> getAll() {
		// TODO Auto-generated method stub
		return housingRepository.findAll();
	}

	@Override
	public void addHousing(Housing housing) {
		housingRepository.save(housing);
	}

	@Override
	public void updateHousing(Housing housing) {
		housingRepository.save(housing);
	}

	@Override
	public void deleteHousing(Housing housing) {
		housingRepository.delete(housing);
	}

	@Override
	public Housing getHousingByID(int id) {
		return housingRepository.findByid(id);
	}

}
