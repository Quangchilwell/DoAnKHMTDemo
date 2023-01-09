package com.DoAnKHMT.restaurantRoom.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.DoAnKHMT.restaurantRoom.Entity.Housing;
import com.DoAnKHMT.restaurantRoom.Entity.OrderRoom;

public interface OrderRoomRepository extends JpaRepository<OrderRoom, Integer>{
	public OrderRoom findByid(int id);
}
