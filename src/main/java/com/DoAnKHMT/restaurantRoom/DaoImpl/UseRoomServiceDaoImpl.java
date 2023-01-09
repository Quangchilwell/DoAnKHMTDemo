package com.DoAnKHMT.restaurantRoom.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.UseRoomServiceDao;
import com.DoAnKHMT.restaurantRoom.Entity.UseRoomService;
import com.DoAnKHMT.restaurantRoom.Repository.UseRoomServiceRepository;

@Repository
public class UseRoomServiceDaoImpl implements UseRoomServiceDao{

	@Autowired
	UseRoomServiceRepository repository;
	
	@Override
	public List<UseRoomService> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void add(UseRoomService useRoomService) {
		// TODO Auto-generated method stub
		repository.save(useRoomService);
	}

	@Override
	public void update(UseRoomService useRoomService) {
		// TODO Auto-generated method stub
		repository.save(useRoomService);
	}

	@Override
	public void delete(UseRoomService useRoomService) {
		// TODO Auto-generated method stub
		repository.delete(useRoomService);
	}

	@Override
	public UseRoomService getByID(int id) {
		// TODO Auto-generated method stub
		return repository.findByid(id);
	}

	@Override
	public List<UseRoomService> getByIdInvoice(int idInvoice) {
		// TODO Auto-generated method stub
		return repository.findByidInvoice(idInvoice);
	}

	@Override
	public List<UseRoomService> getByIdService(int idService) {
		// TODO Auto-generated method stub
		return repository.findByidServiceInRoom(idService);
	}
	
}
