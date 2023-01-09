package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.UseRoomServiceDao;
import com.DoAnKHMT.restaurantRoom.Entity.UseRoomService;
import com.DoAnKHMT.restaurantRoom.Model.InvoiceDTO;
import com.DoAnKHMT.restaurantRoom.Model.ServiceInRoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.UseRoomSeviceDTO;
import com.DoAnKHMT.restaurantRoom.Service.InvoiceService;
import com.DoAnKHMT.restaurantRoom.Service.RoomService;
import com.DoAnKHMT.restaurantRoom.Service.ServiceInRoomService;
import com.DoAnKHMT.restaurantRoom.Service.UseRoomServiceService;

@Service
public class UseRoomServiceServiceImpl implements UseRoomServiceService{

	@Autowired UseRoomServiceDao useRoomServiceDao;
	
	@Autowired InvoiceService invoiceService;
	
	@Autowired ServiceInRoomService serviceInRoomService;
	
	@Autowired RoomService roomService;
	
	private void infoUseRoomSeviceDTO(UseRoomService useRoomService ,UseRoomSeviceDTO useRoomSeviceDTO)
	{
		InvoiceDTO invoiceDTO = invoiceService.getByID(useRoomService.getIdInvoice());
		ServiceInRoomDTO serviceInRoomDTO = serviceInRoomService.getByID(useRoomService.getIdServiceInRoom());
		
		useRoomSeviceDTO.setId(useRoomService.getId());
		useRoomSeviceDTO.setInvoiceDTO(invoiceDTO);
		useRoomSeviceDTO.setServiceInRoomDTO(serviceInRoomDTO);
		useRoomSeviceDTO.setRoomDTO(roomService.getRoomByID(useRoomService.getIdRoom()));
		useRoomSeviceDTO.setQuantity(useRoomService.getQuantity());
		useRoomSeviceDTO.setUnitPrice(useRoomService.getUnitPrice());
		useRoomSeviceDTO.setDescription(useRoomService.getDescription());
	}
	
	@Override
	public List<UseRoomSeviceDTO> getAll() {
		List<UseRoomSeviceDTO> useRoomSeviceDTOs = new ArrayList<UseRoomSeviceDTO>();
		List<UseRoomService> useRoomServices = useRoomServiceDao.getAll();
		
		for(UseRoomService useRoomService: useRoomServices)
		{
			UseRoomSeviceDTO useRoomSeviceDTO = new UseRoomSeviceDTO();
			infoUseRoomSeviceDTO(useRoomService, useRoomSeviceDTO);
			useRoomSeviceDTOs.add(useRoomSeviceDTO);
		}
		
		return useRoomSeviceDTOs;
	}

	@Override
	public void add(UseRoomSeviceDTO useRoomServiceDto) {
		UseRoomService useRoomService = new UseRoomService();
		useRoomService.setIdInvoice(useRoomServiceDto.getInvoiceDTO().getId());
		useRoomService.setIdServiceInRoom(useRoomServiceDto.getServiceInRoomDTO().getId());
		useRoomService.setIdRoom(useRoomServiceDto.getRoomDTO().getId());
		useRoomService.setQuantity(useRoomServiceDto.getQuantity());
		useRoomService.setUnitPrice(useRoomServiceDto.getUnitPrice());
		useRoomService.setDescription(useRoomServiceDto.getDescription());
		useRoomServiceDao.add(useRoomService);
	}

	@Override
	public void update(UseRoomSeviceDTO useRoomServiceDto) {
		UseRoomService useRoomService = useRoomServiceDao.getByID(useRoomServiceDto.getId());
		if(useRoomService != null)
		{
			useRoomService.setIdInvoice(useRoomServiceDto.getInvoiceDTO().getId());
			useRoomService.setIdServiceInRoom(useRoomServiceDto.getServiceInRoomDTO().getId());
			useRoomService.setIdRoom(useRoomServiceDto.getRoomDTO().getId());
			useRoomService.setQuantity(useRoomServiceDto.getQuantity());
			useRoomService.setUnitPrice(useRoomServiceDto.getUnitPrice());
			useRoomService.setDescription(useRoomServiceDto.getDescription());
			useRoomServiceDao.update(useRoomService);
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		UseRoomService useRoomService = useRoomServiceDao.getByID(id);
		if(useRoomService != null)
		{
			useRoomServiceDao.delete(useRoomService);
		}
	}

	@Override
	public UseRoomSeviceDTO getByID(int id) {
		UseRoomService useRoomService = useRoomServiceDao.getByID(id);
		if(useRoomService != null)
		{
			UseRoomSeviceDTO useRoomSeviceDTO = new UseRoomSeviceDTO();
			infoUseRoomSeviceDTO(useRoomService, useRoomSeviceDTO);
			return useRoomSeviceDTO;
		}
		return null;
	}

	@Override
	public List<UseRoomSeviceDTO> getByIdInvoice(int idInvoice) {
		List<UseRoomSeviceDTO> useRoomSeviceDTOs = new ArrayList<UseRoomSeviceDTO>();
		List<UseRoomService> useRoomServices = useRoomServiceDao.getByIdInvoice(idInvoice);
		
		for(UseRoomService useRoomService: useRoomServices)
		{
			UseRoomSeviceDTO useRoomSeviceDTO = new UseRoomSeviceDTO();
			infoUseRoomSeviceDTO(useRoomService, useRoomSeviceDTO);
			useRoomSeviceDTOs.add(useRoomSeviceDTO);
		}
		
		return useRoomSeviceDTOs;
	}

	@Override
	public List<UseRoomSeviceDTO> getByIdService(int idService) {
		List<UseRoomSeviceDTO> useRoomSeviceDTOs = new ArrayList<UseRoomSeviceDTO>();
		List<UseRoomService> useRoomServices = useRoomServiceDao.getByIdService(idService);
		
		for(UseRoomService useRoomService: useRoomServices)
		{
			UseRoomSeviceDTO useRoomSeviceDTO = new UseRoomSeviceDTO();
			infoUseRoomSeviceDTO(useRoomService, useRoomSeviceDTO);
			useRoomSeviceDTOs.add(useRoomSeviceDTO);
		}
		
		return useRoomSeviceDTOs;
	}

}
