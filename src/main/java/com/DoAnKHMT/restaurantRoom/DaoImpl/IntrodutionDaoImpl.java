package com.DoAnKHMT.restaurantRoom.DaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.IntrodutionDao;
import com.DoAnKHMT.restaurantRoom.Entity.Introdution;
import com.DoAnKHMT.restaurantRoom.Repository.IntrodutionRepository;

@Repository
public class IntrodutionDaoImpl implements IntrodutionDao{

	@Autowired IntrodutionRepository repository;
	
	@Override
	public void update(Introdution introdution) {
		repository.save(introdution);
	}

	@Override
	public Introdution getIntro(int id) {
		return repository.findByid(id);
	}

}
