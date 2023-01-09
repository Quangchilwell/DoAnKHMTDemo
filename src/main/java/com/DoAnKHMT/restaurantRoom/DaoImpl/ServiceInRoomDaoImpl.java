package com.DoAnKHMT.restaurantRoom.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.ServiceInRoomDao;
import com.DoAnKHMT.restaurantRoom.Entity.ServiceInRoom;
import com.DoAnKHMT.restaurantRoom.Repository.ServiceInRoomRepository;

@Repository
public class ServiceInRoomDaoImpl implements ServiceInRoomDao{

	@Autowired
	ServiceInRoomRepository serviceInRoomRepository;
	
	@Override
	public List<ServiceInRoom> getAll() {
		return serviceInRoomRepository.findAll();
	}

	@Override
	public void addServiceInRoom(ServiceInRoom serviceInRoom) {
		serviceInRoomRepository.save(serviceInRoom);
	}

	@Override
	public void updateServiceInRoom(ServiceInRoom serviceInRoom) {
		serviceInRoomRepository.save(serviceInRoom);
	}

	@Override
	public void deleteServiceInRoom(ServiceInRoom serviceInRoom) {
		serviceInRoomRepository.delete(serviceInRoom);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ServiceInRoom getByID(int id) {
		// TODO Auto-generated method stub
		return serviceInRoomRepository.findByid(id);
	}

	@Override
	public List<ServiceInRoom> getByNameLike(String name) {
		return serviceInRoomRepository.findByNameLike(name);
	}
	
}
