package com.DoAnKHMT.restaurantRoom.DaoImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.RoomDao;
import com.DoAnKHMT.restaurantRoom.Entity.Room;
import com.DoAnKHMT.restaurantRoom.Repository.RoomRepository;

@Repository
public class RoomDaoImpl implements RoomDao{

	@Autowired
	RoomRepository roomRepository;
	
	@Override
	public List<Room> getAll() {
		return roomRepository.findAll();
	}
	
	@Override
	public List<Room> getByRoomType(int idType) {
		// TODO Auto-generated method stub
		return roomRepository.findRoomByidType(idType);
	}
	
	@Override
	public List<Room> getByHousing(int idHousing) {
		// TODO Auto-generated method stub
		return roomRepository.findRoomByidHousing(idHousing);
	}
	
	@Override
	public List<Room> getRoomsAvailable(int idOrder) {
		return roomRepository.findRoomsAvailable(idOrder);
	}
	
	@Override
	public List<Room> getRoomsAvailableInInvoice(Timestamp startDate, Timestamp endDate) {
		// TODO Auto-generated method stub
		return roomRepository.findRoomsAvailableFoInvoice(startDate, endDate);
	}

	@Override
	public void addRoom(Room room) {
		roomRepository.save(room);
	}

	@Override
	public void updateRoom(Room room) {
		roomRepository.save(room);
	}

	@Override
	public void deleteRoom(Room room) {
		roomRepository.delete(room);
	}

	@Override
	public Room getRoomByID(int id) {
		return roomRepository.findByid(id);
		
	}


}
