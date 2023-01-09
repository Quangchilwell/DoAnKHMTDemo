package com.DoAnKHMT.restaurantRoom.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnKHMT.restaurantRoom.Entity.Housing;
import com.DoAnKHMT.restaurantRoom.Entity.ServiceInRoom;

public interface ServiceInRoomRepository extends JpaRepository<ServiceInRoom, Integer>{
	public ServiceInRoom findByid(int id);
	
	public List<ServiceInRoom> findByNameLike(String info);
}
