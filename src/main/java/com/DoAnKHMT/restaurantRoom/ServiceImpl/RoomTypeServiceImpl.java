package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.RoomTypeDao;
import com.DoAnKHMT.restaurantRoom.Entity.RoomType;
import com.DoAnKHMT.restaurantRoom.Model.RoomTypeDTO;
import com.DoAnKHMT.restaurantRoom.Service.RoomTypeService;

@Service
public class RoomTypeServiceImpl implements RoomTypeService{

	@Autowired
	RoomTypeDao roomTypeDao;
	
	@Override
	public void addRoomType(RoomTypeDTO roomTypeDTO) {
		RoomType roomType = new RoomType();
		roomType.setName(roomTypeDTO.getName());
		roomType.setImg(roomTypeDTO.getImage());
		roomType.setPrice(roomTypeDTO.getPrice());
		roomType.setDescription(roomTypeDTO.getDescription());
		roomTypeDao.addRoomType(roomType);
		
	}

	@Override
	public void updateRoomType(RoomTypeDTO roomTypeDTO) {
		RoomType roomType = roomTypeDao.getRoomTypeByID(roomTypeDTO.getId());
		if(roomType != null) {
			roomType.setName(roomTypeDTO.getName());
			roomType.setImg(roomTypeDTO.getImage());
			roomType.setPrice(roomTypeDTO.getPrice());
			roomType.setDescription(roomTypeDTO.getDescription());
			roomTypeDao.updateRoomType(roomType);			
		}
		
	}

	@Override
	public void deleteRoomType(int id) {
		RoomType roomType = roomTypeDao.getRoomTypeByID(id);
		if(roomType != null) {
			roomTypeDao.deleteRoomType(roomType);
		}
		
	}

	@Override
	public List<RoomTypeDTO> getAll() {
		List<RoomTypeDTO> roomTypeDTOs = new ArrayList<RoomTypeDTO>();
		List<RoomType> roomTypes = roomTypeDao.getAll();
		
		for(RoomType roomType: roomTypes)
		{
			RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
			roomTypeDTO.setId(roomType.getId());
			roomTypeDTO.setName(roomType.getName());
			roomTypeDTO.setPrice(roomType.getPrice());
			roomTypeDTO.setDescription(roomType.getDescription());
			roomTypeDTO.setImage(roomType.getImg());
			roomTypeDTOs.add(roomTypeDTO);
		}
		return roomTypeDTOs;
	}

	@Override
	public RoomTypeDTO getRoomTypeByID(int id) {
		RoomType roomType = roomTypeDao.getRoomTypeByID(id);
		if(roomType != null) {
			RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
			roomTypeDTO.setId(roomType.getId());
			roomTypeDTO.setName(roomType.getName());
			roomTypeDTO.setPrice(roomType.getPrice());
			roomTypeDTO.setImage(roomType.getImg());
			roomTypeDTO.setDescription(roomType.getDescription());
			return roomTypeDTO;
		}
		return null;
	}

}
