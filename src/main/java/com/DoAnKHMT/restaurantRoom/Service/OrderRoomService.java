package com.DoAnKHMT.restaurantRoom.Service;

import java.util.List;
import com.DoAnKHMT.restaurantRoom.Model.OrderRoomDTO;

public interface OrderRoomService {
	public List<OrderRoomDTO> getAll();
	
	public List<OrderRoomDTO> getOrderAccepted();
	
	public List<OrderRoomDTO> getOrderRoomBySearching(String info);
	
	public void add(OrderRoomDTO orderRoomDTO);
	
	public void update(OrderRoomDTO orderRoomDTO);
	
	public void delete(int id);
	
	public OrderRoomDTO getByID(int id);
}
