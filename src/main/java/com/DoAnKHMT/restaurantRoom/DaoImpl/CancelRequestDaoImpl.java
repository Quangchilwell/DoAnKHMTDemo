package com.DoAnKHMT.restaurantRoom.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.CancelRequestDao;
import com.DoAnKHMT.restaurantRoom.Entity.CancelRequest;
import com.DoAnKHMT.restaurantRoom.Repository.CancelRequestRepository;

@Repository
public class CancelRequestDaoImpl implements CancelRequestDao{

	@Autowired CancelRequestRepository repository;
	
	@Override
	public List<CancelRequest> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void add(CancelRequest cancelRequest) {
		// TODO Auto-generated method stub
		repository.save(cancelRequest);
	}

	@Override
	public void update(CancelRequest cancelRequest) {
		// TODO Auto-generated method stub
		repository.save(cancelRequest);
	}

	@Override
	public void delete(CancelRequest cancelRequest) {
		// TODO Auto-generated method stub
		repository.delete(cancelRequest);
	}

	@Override
	public CancelRequest getByID(int id) {
		// TODO Auto-generated method stub
		return repository.findByid(id);
	}

}
