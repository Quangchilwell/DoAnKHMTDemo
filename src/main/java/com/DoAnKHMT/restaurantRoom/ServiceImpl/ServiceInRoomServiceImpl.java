package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.ServiceInRoomDao;
import com.DoAnKHMT.restaurantRoom.Entity.ServiceInRoom;
import com.DoAnKHMT.restaurantRoom.Model.ServiceInRoomDTO;
import com.DoAnKHMT.restaurantRoom.Service.ServiceInRoomService;

@Service
public class ServiceInRoomServiceImpl implements ServiceInRoomService{

	@Autowired
	ServiceInRoomDao serviceInRoomDao;
	
	@Override
	public List<ServiceInRoomDTO> getAll() {
		List<ServiceInRoom> serviceInRooms = serviceInRoomDao.getAll();
		List<ServiceInRoomDTO> serviceInRoomDTOs = new ArrayList<ServiceInRoomDTO>();
		
		for(ServiceInRoom serviceInRoom: serviceInRooms)
		{
			ServiceInRoomDTO serviceInRoomDTO = new ServiceInRoomDTO();
			serviceInRoomDTO.setId(serviceInRoom.getId());
			serviceInRoomDTO.setPrice(serviceInRoom.getPrice());
			serviceInRoomDTO.setUnit(serviceInRoom.getUnit());
			serviceInRoomDTO.setName(serviceInRoom.getName());
			serviceInRoomDTO.setDescription(serviceInRoom.getDescription());
			serviceInRoomDTOs.add(serviceInRoomDTO);
		}
		
		return serviceInRoomDTOs;
	}

	@Override
	public void addServiceInRoom(ServiceInRoomDTO serviceInRoomDTO) {
		ServiceInRoom serviceInRoom = new ServiceInRoom();
		
		serviceInRoom.setName(serviceInRoomDTO.getName());
		serviceInRoom.setDescription(serviceInRoomDTO.getDescription());
		serviceInRoom.setPrice(serviceInRoomDTO.getPrice());
		serviceInRoom.setUnit(serviceInRoomDTO.getUnit());
		
		serviceInRoomDao.addServiceInRoom(serviceInRoom);
	}

	@Override
	public void updateServiceInRoom(ServiceInRoomDTO serviceInRoomDTO) {
		ServiceInRoom serviceInRoom = serviceInRoomDao.getByID(serviceInRoomDTO.getId());
		if(serviceInRoom != null) {
			serviceInRoom.setName(serviceInRoomDTO.getName());
			serviceInRoom.setPrice(serviceInRoomDTO.getPrice());
			serviceInRoom.setUnit(serviceInRoomDTO.getUnit());
			serviceInRoom.setDescription(serviceInRoomDTO.getDescription());
			serviceInRoomDao.updateServiceInRoom(serviceInRoom);			
		}
	}

	@Override
	public void deleteServiceInRoom(int id) {
		ServiceInRoom serviceInRoom = serviceInRoomDao.getByID(id);
		if(serviceInRoom != null) {
			serviceInRoomDao.deleteServiceInRoom(serviceInRoom);
		}
	}

	@Override
	public ServiceInRoomDTO getByID(int id) {
		ServiceInRoom serviceInRoom = serviceInRoomDao.getByID(id);
		if(serviceInRoom != null) {
			ServiceInRoomDTO serviceInRoomDTO = new ServiceInRoomDTO();
			serviceInRoomDTO.setId(serviceInRoom.getId());
			serviceInRoomDTO.setPrice(serviceInRoom.getPrice());
			serviceInRoomDTO.setUnit(serviceInRoom.getUnit());
			serviceInRoomDTO.setName(serviceInRoom.getName());
			serviceInRoomDTO.setDescription(serviceInRoom.getDescription());
			return serviceInRoomDTO;
		}
		return null;
	}

	@Override
	public List<ServiceInRoomDTO> getByNameInclude(String info) {
		List<ServiceInRoom> serviceInRooms = serviceInRoomDao.getAll();
		List<ServiceInRoomDTO> serviceInRoomDTOs = new ArrayList<ServiceInRoomDTO>();
		
		for(ServiceInRoom serviceInRoom: serviceInRooms)
		{
			if(serviceInRoom.getName().contains(info))
			{
				ServiceInRoomDTO serviceInRoomDTO = new ServiceInRoomDTO();
				serviceInRoomDTO.setId(serviceInRoom.getId());
				serviceInRoomDTO.setPrice(serviceInRoom.getPrice());
				serviceInRoomDTO.setUnit(serviceInRoom.getUnit());
				serviceInRoomDTO.setName(serviceInRoom.getName());
				serviceInRoomDTO.setDescription(serviceInRoom.getDescription());
				serviceInRoomDTOs.add(serviceInRoomDTO);				
			}
		}
		return serviceInRoomDTOs;
	}

}
