package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.RoomBusyDao;
import com.DoAnKHMT.restaurantRoom.Entity.RoomBusy;
import com.DoAnKHMT.restaurantRoom.Model.InvoiceDTO;
import com.DoAnKHMT.restaurantRoom.Model.OrderRoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomBusyDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.StatusDTO;
import com.DoAnKHMT.restaurantRoom.Service.InvoiceService;
import com.DoAnKHMT.restaurantRoom.Service.OrderRoomService;
import com.DoAnKHMT.restaurantRoom.Service.RoomBusyService;
import com.DoAnKHMT.restaurantRoom.Service.RoomService;
import com.DoAnKHMT.restaurantRoom.Service.StatusService;

@Service
public class RoomBusyServiceImpl implements RoomBusyService {

	@Autowired
	RoomBusyDao roomBusyDao;

	@Autowired
	RoomService roomService;

	@Autowired
	StatusService statusService;

	@Autowired
	OrderRoomService orderRoomService;

	@Autowired
	InvoiceService invoiceService;

	private void inFoRoomBusyDTO(RoomBusy roomBusy, RoomBusyDTO roomBusyDTO) {
		RoomDTO roomDTO = roomService.getRoomByID(roomBusy.getIdRoom());
		StatusDTO statusDTO = statusService.getByID(roomBusy.getIdStatus());
		OrderRoomDTO orderRoomDTO = orderRoomService.getByID(roomBusy.getIdOrder());
		InvoiceDTO invoiceDTO = invoiceService.getByID(roomBusy.getIdInvoice());

		roomBusyDTO.setId(roomBusy.getId());
		roomBusyDTO.setRoomDTO(roomDTO);
		roomBusyDTO.setStatusDTO(statusDTO);
		roomBusyDTO.setOrderRoomDTO(orderRoomDTO);
		roomBusyDTO.setInvoiceDTO(invoiceDTO);
		roomBusyDTO.setStartDate(String.valueOf(roomBusy.getStartDate()));
		roomBusyDTO.setEndDate(String.valueOf(roomBusy.getEndDate()));
		roomBusyDTO.setDescription(roomBusy.getDescription());
		roomBusyDTO.setDaysBooked(roomBusy.getDaysBooked());
		roomBusyDTO.setUnitPrice(roomBusy.getUnitPrice());
	}

	@Override
	public List<RoomBusyDTO> getAll() {
		List<RoomBusyDTO> roomBusyDTOs = new ArrayList<RoomBusyDTO>();
		List<RoomBusy> roomBusies = roomBusyDao.getAll();
		for (RoomBusy roomBusy : roomBusies) {
			RoomBusyDTO roomBusyDTO = new RoomBusyDTO();
			inFoRoomBusyDTO(roomBusy, roomBusyDTO);
			roomBusyDTOs.add(roomBusyDTO);
		}

		return roomBusyDTOs;
	}

	@Override
	public void add(RoomBusyDTO roomBusyDTO) {
		RoomBusy roomBusy = new RoomBusy();
		roomBusy.setIdRoom(roomBusyDTO.getRoomDTO().getId());
		if (roomBusyDTO.getOrderRoomDTO() != null) {
			roomBusy.setIdOrder(roomBusyDTO.getOrderRoomDTO().getId());
		}
		if (roomBusyDTO.getInvoiceDTO() != null) {
			roomBusy.setIdInvoice(roomBusyDTO.getInvoiceDTO().getId());
		}

		String startDate = roomBusyDTO.getStartDate();
		String endDate = roomBusyDTO.getEndDate();

		// Cap nhat ngay
		if (startDate.contains("T") || endDate.contains("T")) {
			roomBusy.setStartDate(Timestamp.valueOf(LocalDateTime.parse(startDate)));
			roomBusy.setEndDate(Timestamp.valueOf(LocalDateTime.parse(endDate)));
		} else {
			roomBusy.setStartDate(Timestamp.valueOf(startDate));
			roomBusy.setEndDate(Timestamp.valueOf(endDate));
		}

		roomBusy.setIdRoom(roomBusyDTO.getRoomDTO().getId());
		roomBusy.setIdStatus(roomBusyDTO.getStatusDTO().getId());
		roomBusy.setDescription(roomBusyDTO.getDescription());
		roomBusy.setDaysBooked(roomBusyDTO.getDaysBooked());
		roomBusy.setUnitPrice(roomBusyDTO.getUnitPrice());
		
		roomBusyDao.add(roomBusy);

	}

	@Override
	public void update(RoomBusyDTO roomBusyDTO) {
		RoomBusy roomBusy = roomBusyDao.getByID(roomBusyDTO.getId());
		if (roomBusy != null) {
			String startDate = roomBusyDTO.getStartDate();
			String endDate = roomBusyDTO.getEndDate();

			roomBusy.setIdRoom(roomBusyDTO.getRoomDTO().getId());
			if (roomBusyDTO.getInvoiceDTO() != null) {
				roomBusy.setIdInvoice(roomBusyDTO.getInvoiceDTO().getId());
			}

			if (roomBusyDTO.getOrderRoomDTO() == null) {
				roomBusy.setIdOrder(0);
			} else {
				roomBusy.setIdOrder(roomBusyDTO.getOrderRoomDTO().getId());
			}

			// Cap nhat ngay
			if (startDate.contains("T") || endDate.contains("T")) {
				roomBusy.setStartDate(Timestamp.valueOf(LocalDateTime.parse(startDate)));
				roomBusy.setEndDate(Timestamp.valueOf(LocalDateTime.parse(endDate)));
			} else {
				roomBusy.setStartDate(Timestamp.valueOf(startDate));
				roomBusy.setEndDate(Timestamp.valueOf(endDate));
			}
			roomBusy.setIdRoom(roomBusyDTO.getRoomDTO().getId());
			roomBusy.setIdStatus(roomBusyDTO.getStatusDTO().getId());
			roomBusy.setDescription(roomBusyDTO.getDescription());
			roomBusy.setDaysBooked(roomBusyDTO.getDaysBooked());
			roomBusy.setUnitPrice(roomBusyDTO.getUnitPrice());
			roomBusyDao.update(roomBusy);
		}

	}

	@Override
	public void delete(int id) {
		RoomBusy roomBusy = roomBusyDao.getByID(id);
		if (roomBusy != null) {
			roomBusyDao.delete(roomBusy);
		}
	}

	@Override
	public RoomBusyDTO getByID(int id) {
		RoomBusy roomBusy = roomBusyDao.getByID(id);
		if (roomBusy != null) {
			RoomBusyDTO roomBusyDTO = new RoomBusyDTO();
			inFoRoomBusyDTO(roomBusy, roomBusyDTO);
			return roomBusyDTO;
		}
		return null;
	}

	@Override
	public List<RoomBusyDTO> getByIdOrder(int idOrder) {
		List<RoomBusyDTO> roomBusyDTOs = new ArrayList<RoomBusyDTO>();
		List<RoomBusy> roomBusies = roomBusyDao.getByIdOrder(idOrder);
		for (RoomBusy roomBusy : roomBusies) {
			RoomBusyDTO roomBusyDTO = new RoomBusyDTO();
			inFoRoomBusyDTO(roomBusy, roomBusyDTO);
			roomBusyDTOs.add(roomBusyDTO);
		}

		return roomBusyDTOs;
	}

	@Override
	public List<RoomBusyDTO> getByIdInvoice(int idInvoice) {
		List<RoomBusyDTO> roomBusyDTOs = new ArrayList<RoomBusyDTO>();
		List<RoomBusy> roomBusies = roomBusyDao.getByIdInvoice(idInvoice);
		for (RoomBusy roomBusy : roomBusies) {
			RoomBusyDTO roomBusyDTO = new RoomBusyDTO();
			inFoRoomBusyDTO(roomBusy, roomBusyDTO);
			roomBusyDTOs.add(roomBusyDTO);
		}
		return roomBusyDTOs;
	}

	@Override
	public List<RoomBusyDTO> getByIdRoom(int idRoom) {
		List<RoomBusyDTO> roomBusyDTOs = new ArrayList<RoomBusyDTO>();
		List<RoomBusy> roomBusies = roomBusyDao.getByIdRoom(idRoom);
		for (RoomBusy roomBusy : roomBusies) {
			RoomBusyDTO roomBusyDTO = new RoomBusyDTO();
			inFoRoomBusyDTO(roomBusy, roomBusyDTO);
			roomBusyDTOs.add(roomBusyDTO);
		}
		return roomBusyDTOs;
	}

}
