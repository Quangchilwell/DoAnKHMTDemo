package com.DoAnKHMT.restaurantRoom.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.ManagerDao;
import com.DoAnKHMT.restaurantRoom.Entity.Manager;
import com.DoAnKHMT.restaurantRoom.Repository.ManagerRepository;

@Repository
public class ManagerDaoImpl implements ManagerDao{

	@Autowired ManagerRepository repository;
	
	@Override
	public List<Manager> getAll() {
		return repository.findAll();
	}

	@Override
	public void add(Manager manager) {
		repository.save(manager);
	}

	@Override
	public void update(Manager manager) {
		repository.save(manager);
	}

	@Override
	public void delete(Manager manager) {
		repository.delete(manager);
	}

	@Override
	public Manager getByID(int id) {
		return repository.findByid(id);
	}

	@Override
	public Manager getByUsername(String username) {
		// TODO Auto-generated method stub
		return repository.findByuserName(username);
	}

}
