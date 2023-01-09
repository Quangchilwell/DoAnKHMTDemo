package com.DoAnKHMT.restaurantRoom.Repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnKHMT.restaurantRoom.Entity.RoomBusy;

public interface RoomBusyRepository extends JpaRepository<RoomBusy, Integer>{
	public RoomBusy findByid(int id);
	
	public List<RoomBusy> findByidOrder(int idOrder);
	
	public List<RoomBusy> findByidInvoice(int idInvoice);
	
	public List<RoomBusy> findByidRoom(int idRoom, Sort sort);
//	Sort sort = Sort.by("startDate").ascending().and(Sort.by("endDate").ascending());
}
