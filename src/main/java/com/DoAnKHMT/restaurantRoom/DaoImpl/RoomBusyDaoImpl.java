package com.DoAnKHMT.restaurantRoom.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.RoomBusyDao;
import com.DoAnKHMT.restaurantRoom.Entity.RoomBusy;
import com.DoAnKHMT.restaurantRoom.Repository.RoomBusyRepository;

@Repository
public class RoomBusyDaoImpl implements RoomBusyDao{

	@Autowired
	RoomBusyRepository roomBusyRepository;
	
	@Override
	public List<RoomBusy> getAll() {
		return roomBusyRepository.findAll();
	}

	@Override
	public void add(RoomBusy roomBusy) {
		roomBusyRepository.save(roomBusy);
	}

	@Override
	public void update(RoomBusy roomBusy) {
		roomBusyRepository.save(roomBusy);
	}

	@Override
	public void delete(RoomBusy roomBusy) {
		roomBusyRepository.delete(roomBusy);
	}

	@Override
	public RoomBusy getByID(int id) {
		// TODO Auto-generated method stub
		return roomBusyRepository.findByid(id);
	}

	@Override
	public List<RoomBusy> getByIdOrder(int idOrder) {
		// TODO Auto-generated method stub
		return roomBusyRepository.findByidOrder(idOrder);
	}

	@Override
	public List<RoomBusy> getByIdInvoice(int idInvoice) {
		// TODO Auto-generated method stub
		return roomBusyRepository.findByidInvoice(idInvoice);
	}

	@Override
	public List<RoomBusy> getByIdRoom(int idRoom) {
		Sort sort = Sort.by("startDate").ascending().and(Sort.by("endDate").ascending());
		return roomBusyRepository.findByidRoom(idRoom, sort);
	}

}
