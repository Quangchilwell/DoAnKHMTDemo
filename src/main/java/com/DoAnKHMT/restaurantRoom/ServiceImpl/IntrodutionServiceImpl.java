package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.IntrodutionDao;
import com.DoAnKHMT.restaurantRoom.Entity.Introdution;
import com.DoAnKHMT.restaurantRoom.Model.IntrodutionDTO;
import com.DoAnKHMT.restaurantRoom.Service.IntrodutionService;

@Service
public class IntrodutionServiceImpl implements IntrodutionService{

	@Autowired
	IntrodutionDao introdutionDao;
	
	@Override
	public IntrodutionDTO getIntro(int id) {
		id = 1;
		Introdution introdution = introdutionDao.getIntro(id);
		IntrodutionDTO introdutionDTO = new IntrodutionDTO();
		
		introdutionDTO.setId(id);
		introdutionDTO.setHotelName(introdution.getName());
		introdutionDTO.setAddress(introdution.getAddress());
		introdutionDTO.setPhone(introdution.getPhone());
		introdutionDTO.setEmail(introdution.getEmail());
		
		return introdutionDTO;
	}

	@Override
	public void update(IntrodutionDTO introdutionDTO) {
		int id = 1;
		Introdution introdution = introdutionDao.getIntro(id);
		if(introdution != null) {
			introdution.setId(id);
			introdution.setName(introdutionDTO.getHotelName());
			introdution.setAddress(introdutionDTO.getAddress());
			introdution.setEmail(introdutionDTO.getEmail());
			introdution.setPhone(introdutionDTO.getPhone());			
			introdutionDao.update(introdution);
		}
	}
	
}
