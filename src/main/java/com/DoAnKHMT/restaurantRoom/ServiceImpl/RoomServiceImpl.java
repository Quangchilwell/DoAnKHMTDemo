package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.RoomDao;
import com.DoAnKHMT.restaurantRoom.Entity.Room;
import com.DoAnKHMT.restaurantRoom.Model.HousingDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomTypeDTO;
import com.DoAnKHMT.restaurantRoom.Service.HousingService;
import com.DoAnKHMT.restaurantRoom.Service.RoomService;
import com.DoAnKHMT.restaurantRoom.Service.RoomTypeService;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	RoomDao roomDao;
	
	@Autowired 
	HousingService housingService;
	
	@Autowired
	RoomTypeService roomTypeService;
	
	private void getInfoRoomDTO(Room room, RoomDTO roomDTO)
	{
		if(room.getIdType() != 0){
			RoomTypeDTO roomTypeDTO = roomTypeService.getRoomTypeByID(room.getIdType());
			roomDTO.setRoomTypeDTO(roomTypeDTO);
		}
		else {
			RoomTypeDTO roomTypeDTO = null;
			roomDTO.setRoomTypeDTO(roomTypeDTO);
		}
		HousingDTO housingDTO = housingService.getHousingByID(room.getIdHousing());
	
		roomDTO.setId(room.getId());
		roomDTO.setName(room.getName());
		roomDTO.setFloor(room.getFloor());
		roomDTO.setStatus(room.getStatus());
		roomDTO.setDescription(room.getDescription());
		roomDTO.setHousingDTO(housingDTO);
	}
	
	@Override
	public List<RoomDTO> getAllRoom() {
		List<RoomDTO> roomDTOs = new ArrayList<RoomDTO>();
		List<Room> rooms = roomDao.getAll();
		
		for(Room room: rooms)
		{
			RoomDTO roomDTO = new RoomDTO();
			getInfoRoomDTO(room, roomDTO);
			roomDTOs.add(roomDTO);
		}
		
		return roomDTOs;
	}

	@Override
	public void addRoom(RoomDTO roomDTO) {
		Room room = new Room();
		room.setName(roomDTO.getName());
		room.setStatus(roomDTO.getStatus());
		room.setFloor(roomDTO.getFloor());
		room.setDescription(roomDTO.getDescription());
		room.setIdType(roomDTO.getRoomTypeDTO().getId());
		room.setIdHousing(roomDTO.getHousingDTO().getId());
		
		roomDao.addRoom(room);
	}

	@Override
	public void updateRoom(RoomDTO roomDTO) {
		Room room = roomDao.getRoomByID(roomDTO.getId());
		if(room != null) {
			if(roomDTO.getRoomTypeDTO() == null) {
				room.setIdType(0);
			}else {
				room.setIdType(roomDTO.getRoomTypeDTO().getId());				
			}
			
			if(roomDTO.getHousingDTO() == null) {
				room.setIdHousing(0);
			}else {
				room.setIdHousing(roomDTO.getHousingDTO().getId());
			}
			
			room.setName(roomDTO.getName());
			room.setStatus(roomDTO.getStatus());
			room.setFloor(roomDTO.getFloor());
			room.setDescription(roomDTO.getDescription());
			roomDao.updateRoom(room);
			
		}
	}

	@Override
	public void deleteRoom(int id) {
		Room room = roomDao.getRoomByID(id);
		if(room != null) {
			roomDao.deleteRoom(room);
		}
	}

	@Override
	public RoomDTO getRoomByID(int id) {
		Room room = roomDao.getRoomByID(id);
		if(room != null) {
			RoomDTO roomDTO = new RoomDTO();
			getInfoRoomDTO(room, roomDTO);
			return roomDTO;
		}
		return null;
	}

	@Override
	public List<RoomDTO> getRoomByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomDTO> getRoomsAvailableForOrder(int idOrder, Timestamp startDate, Timestamp endDate) {
		List<RoomDTO> roomDTOs = new ArrayList<RoomDTO>();
		List<Room> rooms = roomDao.getRoomsAvailableInInvoice(startDate, endDate);
		
		for(Room room: rooms)
		{
			RoomDTO roomDTO = new RoomDTO();
			getInfoRoomDTO(room, roomDTO);
			roomDTOs.add(roomDTO);
		}
		
		return roomDTOs;
	}

	@Override
	public List<RoomDTO> getRoomsAvailableInInvoice(Timestamp startDate, Timestamp endDate) {
		List<RoomDTO> roomDTOs = new ArrayList<RoomDTO>();
		List<Room> rooms = roomDao.getRoomsAvailableInInvoice(startDate, endDate);
		
		for(Room room: rooms)
		{
			RoomDTO roomDTO = new RoomDTO();
			getInfoRoomDTO(room, roomDTO);
			roomDTOs.add(roomDTO);
		}
		
		return roomDTOs;
	}

	@Override
	public List<RoomDTO> getByRoomType(int idType) {
		List<RoomDTO> roomDTOs = new ArrayList<RoomDTO>();
		List<Room> rooms = roomDao.getByRoomType(idType);
		
		for(Room room: rooms)
		{
			RoomDTO roomDTO = new RoomDTO();
			getInfoRoomDTO(room, roomDTO);
			roomDTOs.add(roomDTO);
		}
		
		return roomDTOs;
	}

	@Override
	public List<RoomDTO> getByHousing(int idHousing) {
		List<RoomDTO> roomDTOs = new ArrayList<RoomDTO>();
		List<Room> rooms = roomDao.getByHousing(idHousing);
		
		for(Room room: rooms)
		{
			RoomDTO roomDTO = new RoomDTO();
			getInfoRoomDTO(room, roomDTO);
			roomDTOs.add(roomDTO);
		}
		
		return roomDTOs;
	}

}
