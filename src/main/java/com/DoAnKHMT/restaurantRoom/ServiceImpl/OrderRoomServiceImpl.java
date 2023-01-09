package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.OrderRoomDao;
import com.DoAnKHMT.restaurantRoom.Entity.OrderRoom;
import com.DoAnKHMT.restaurantRoom.Model.OrderRoomDTO;
import com.DoAnKHMT.restaurantRoom.Service.OrderRoomService;

@Service
public class OrderRoomServiceImpl implements OrderRoomService {

	@Autowired
	OrderRoomDao orderRoomDao;

	private void getOrderDTO(OrderRoomDTO orderRoomDTO, OrderRoom orderRoom) {
		orderRoomDTO.setId(orderRoom.getId());
		orderRoomDTO.setPersonName(orderRoom.getPersonName());
		orderRoomDTO.setNumberPeople(orderRoom.getNumberPeople());
		orderRoomDTO.setPhone(orderRoom.getPhone());
		orderRoomDTO.setDeposit(orderRoom.getDeposit());
		orderRoomDTO.setOrderDate(String.valueOf(orderRoom.getOrderDate()));
		orderRoomDTO.setStartDate(String.valueOf(orderRoom.getStartDate()));
		orderRoomDTO.setEndDate(String.valueOf(orderRoom.getEndDate()));
		orderRoomDTO.setDescription(orderRoom.getDescription());
		orderRoomDTO.setStatus(orderRoom.getStatus());
	}

	@Override
	public List<OrderRoomDTO> getAll() {
		List<OrderRoomDTO> orderRoomDTOs = new ArrayList<OrderRoomDTO>();
		List<OrderRoom> orderRooms = orderRoomDao.getAll();

		for (OrderRoom orderRoom : orderRooms) {
			if (orderRoom.getStatus() == 0) {
				OrderRoomDTO orderRoomDTO = new OrderRoomDTO();
				getOrderDTO(orderRoomDTO, orderRoom);
				orderRoomDTOs.add(orderRoomDTO);
			}
		}

		return orderRoomDTOs;
	}

	@Override
	public List<OrderRoomDTO> getOrderAccepted() {
		List<OrderRoomDTO> orderRoomDTOs = new ArrayList<OrderRoomDTO>();
		List<OrderRoom> orderRooms = orderRoomDao.getAll();

		for (OrderRoom orderRoom : orderRooms) {
			if (orderRoom.getStatus() != 0) {
				OrderRoomDTO orderRoomDTO = new OrderRoomDTO();
				getOrderDTO(orderRoomDTO, orderRoom);
				orderRoomDTOs.add(orderRoomDTO);
			}
		}

		return orderRoomDTOs;
	}

	@Override
	public void add(OrderRoomDTO orderRoomDTO) {
		OrderRoom orderRoom = new OrderRoom();
		LocalDateTime localDateTime = LocalDateTime.now();
	
		String startDate = orderRoomDTO.getStartDate();
		String endDate = orderRoomDTO.getEndDate();
		
		if(orderRoomDTO.getStartDate().contains("T") || orderRoomDTO.getEndDate().contains("T")) {
			orderRoom.setStartDate(Timestamp.valueOf(LocalDateTime.parse(startDate)));
			orderRoom.setEndDate((Timestamp.valueOf(LocalDateTime.parse(endDate))));
		}
		else {
			orderRoom.setStartDate(Timestamp.valueOf(startDate + ":00"));
			orderRoom.setEndDate(Timestamp.valueOf(endDate + ":00"));			
		}
		
		orderRoom.setPersonName(orderRoomDTO.getPersonName());
		orderRoom.setPhone(orderRoomDTO.getPhone());
		orderRoom.setNumberPeople(orderRoomDTO.getNumberPeople());
		orderRoom.setDeposit(orderRoomDTO.getDeposit());
		orderRoom.setOrderDate(Timestamp.valueOf(localDateTime));
		orderRoom.setDescription(orderRoomDTO.getDescription());
		orderRoom.setStatus(0);

		orderRoomDao.add(orderRoom);
	}

	@Override
	public void update(OrderRoomDTO orderRoomDTO) {
		OrderRoom orderRoom = orderRoomDao.getByID(orderRoomDTO.getId());		
		if (orderRoom != null) {
			String startDate = orderRoomDTO.getStartDate();
			String endDate = orderRoomDTO.getEndDate();
			
			if(orderRoomDTO.getStartDate().contains("T") || orderRoomDTO.getEndDate().contains("T")) {
				orderRoom.setStartDate(Timestamp.valueOf(LocalDateTime.parse(startDate)));
				orderRoom.setEndDate(Timestamp.valueOf(LocalDateTime.parse(endDate)));
			}
			else {
				orderRoom.setStartDate(Timestamp.valueOf(startDate));
				orderRoom.setEndDate(Timestamp.valueOf(endDate));
			}

			orderRoom.setPersonName(orderRoomDTO.getPersonName());
			orderRoom.setPhone(orderRoomDTO.getPhone());
			orderRoom.setNumberPeople(orderRoomDTO.getNumberPeople());
			orderRoom.setDeposit(orderRoomDTO.getDeposit());
			orderRoom.setDescription(orderRoomDTO.getDescription());
			orderRoom.setStatus(orderRoomDTO.getStatus());
			orderRoomDao.update(orderRoom);
		}
	}

	@Override
	public void delete(int id) {
		OrderRoom orderRoom = orderRoomDao.getByID(id);
		if (orderRoom != null) {
			orderRoomDao.delete(orderRoom);
		}
	}

	@Override
	public OrderRoomDTO getByID(int id) {
		OrderRoom orderRoom = orderRoomDao.getByID(id);
		if (orderRoom != null) {
			OrderRoomDTO orderRoomDTO = new OrderRoomDTO();
			getOrderDTO(orderRoomDTO, orderRoom);
			return orderRoomDTO;
		}
		return null;
	}

	@Override
	public List<OrderRoomDTO> getOrderRoomBySearching(String info) {
		List<OrderRoomDTO> orderRoomDTOs = new ArrayList<OrderRoomDTO>();
		List<OrderRoom> orderRooms = orderRoomDao.getAll();
		for(OrderRoom orderRoom: orderRooms) {
			if(orderRoom.getStatus() != 0) {
				if(orderRoom.getPersonName().contains(info) || orderRoom.getPhone().equals(info)) {
					OrderRoomDTO orderRoomDTO = new OrderRoomDTO();
					getOrderDTO(orderRoomDTO, orderRoom);
					orderRoomDTOs.add(orderRoomDTO);
				}				
			}
		}
		return orderRoomDTOs;
	}

}
