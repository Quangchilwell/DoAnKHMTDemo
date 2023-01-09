package com.DoAnKHMT.restaurantRoom.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.RoomTypeDao;
import com.DoAnKHMT.restaurantRoom.Entity.RoomType;
import com.DoAnKHMT.restaurantRoom.Repository.RoomTypeRepositoy;

@Repository
public class RoomTypeDaoImpl implements RoomTypeDao{

	@Autowired
	RoomTypeRepositoy roomTypeRepositoy;
	
	@Override
	public void addRoomType(RoomType roomType) {
		roomTypeRepositoy.save(roomType);
	}

	@Override
	public void updateRoomType(RoomType roomType) {
		roomTypeRepositoy.save(roomType);
	}

	@Override
	public void deleteRoomType(RoomType roomType) {
		roomTypeRepositoy.delete(roomType);
	}

	@Override
	public List<RoomType> getAll() {
		return roomTypeRepositoy.findAll();
	}

	@SuppressWarnings("deprecation")
	@Override
	public RoomType getRoomTypeByID(int id) {
		return roomTypeRepositoy.findByid(id);
	}

}
