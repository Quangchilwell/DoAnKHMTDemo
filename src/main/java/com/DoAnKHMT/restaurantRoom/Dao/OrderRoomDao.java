package com.DoAnKHMT.restaurantRoom.Dao;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Entity.OrderRoom;

public interface OrderRoomDao {
	public List<OrderRoom> getAll();
	
	public void add(OrderRoom orderRoom);
	
	public void update(OrderRoom orderRoom);
	
	public void delete(OrderRoom orderRoom);
	
	public OrderRoom getByID(int id);
}
