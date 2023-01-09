package com.DoAnKHMT.restaurantRoom.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnKHMT.restaurantRoom.Entity.UseRoomService;

public interface UseRoomServiceRepository extends JpaRepository<UseRoomService, Integer>{
	public UseRoomService findByid(int id);
	
	public List<UseRoomService> findByidServiceInRoom(int idServiceInRoom);
	
	public List<UseRoomService> findByidInvoice(int idInvoice);

}
