package com.DoAnKHMT.restaurantRoom.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.OrderRoomDao;
import com.DoAnKHMT.restaurantRoom.Entity.OrderRoom;
import com.DoAnKHMT.restaurantRoom.Repository.OrderRoomRepository;

@Repository
public class OrderRoomDaoImpl implements OrderRoomDao {

	@Autowired
	OrderRoomRepository orderRoomRepository;

	@Override
	public List<OrderRoom> getAll() {
		// TODO Auto-generated method stub
		return orderRoomRepository.findAll();
	}

	@Override
	public void add(OrderRoom orderRoom) {
		orderRoomRepository.save(orderRoom);

	}

	@Override
	public void update(OrderRoom orderRoom) {
		orderRoomRepository.save(orderRoom);
	}

	@Override
	public void delete(OrderRoom orderRoom) {
		orderRoomRepository.delete(orderRoom);
	}

	@Override
	public OrderRoom getByID(int id) {
		// TODO Auto-generated method stub
		return orderRoomRepository.findByid(id);
	}


}
