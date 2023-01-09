package com.DoAnKHMT.restaurantRoom.Controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DoAnKHMT.restaurantRoom.Dao.RoomDao;
import com.DoAnKHMT.restaurantRoom.Entity.Room;
import com.DoAnKHMT.restaurantRoom.Model.HousingDTO;
import com.DoAnKHMT.restaurantRoom.Model.InvoiceDTO;
import com.DoAnKHMT.restaurantRoom.Model.OrderRoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomBusyDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomTypeDTO;
import com.DoAnKHMT.restaurantRoom.Service.HousingService;
import com.DoAnKHMT.restaurantRoom.Service.InvoiceService;
import com.DoAnKHMT.restaurantRoom.Service.OrderRoomService;
import com.DoAnKHMT.restaurantRoom.Service.RoomBusyService;
import com.DoAnKHMT.restaurantRoom.Service.RoomService;
import com.DoAnKHMT.restaurantRoom.Service.RoomTypeService;
import com.DoAnKHMT.restaurantRoom.Service.StatusService;

@Controller
@RequestMapping("/admin")
public class RoomController {
	
	@Autowired
	RoomTypeService roomTypeService;
	
	@Autowired
	HousingService housingService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	RoomDao roomDao;
	
	@Autowired OrderRoomService orderRoomService;
	
	@Autowired RoomBusyService roomBusyService;
	
	@Autowired StatusService statusService;
	
	@Autowired InvoiceService invoiceService;
	
	@GetMapping("/room-list")
	public String roomList(Model model, HttpSession session)
	{
		List<RoomDTO> roomDTOs = roomService.getAllRoom();
		model.addAttribute("roomDTOs", roomDTOs);
		return "room/roomList";
	}
	
//	Danh sach phong trong danh muc
//	@GetMapping("/room-in-type/{idType}")
//	public String roomList(Model model, @PathVariable(name = "idType") int idType)
//	{
//		List<RoomDTO> roomDTOs = roomService.getByRoomType(idType);
//		model.addAttribute("roomDTOs", roomDTOs);
//		return "room/roomInType";
//	}
	
//	Danh sach phong co san, ko ban danh cho DON DAT
	@GetMapping("/rooms-available/idOrder/{id}")
	public String roomsAvailable(Model model, @PathVariable(name = "id") int idOrder,
			@RequestParam(name = "startDate", required = false) String startDate,
			@RequestParam(name = "endDate", required = false) String endDate)
	{	
		OrderRoomDTO orderRoomDTO = orderRoomService.getByID(idOrder);
		model.addAttribute("orderRoom" ,orderRoomDTO);
//		model.addAttribute("startDate", orderRoomDTO.getStartDate());
//		model.addAttribute("endDate", orderRoomDTO.getEndDate());

		if(startDate != null && endDate != null)
		{
			Timestamp startDateTimestamp = Timestamp.valueOf(startDate);
			Timestamp endDateTimestamp = Timestamp.valueOf(endDate);
			List<RoomDTO> roomDTOs = 
					roomService.getRoomsAvailableInInvoice(startDateTimestamp, endDateTimestamp);
//			model.addAttribute("startDate", startDate);
//			model.addAttribute("endDate", endDate);
			model.addAttribute("roomDTOs", roomDTOs);
		}
		return "room/roomsAvailable";
	}
	
	
//	Sap xep phong cho don dat
	@GetMapping("/selectRooms")
	public String selectRoomForOrder(Model model, @RequestParam(name = "idOrder") int idOrder,
			@RequestParam(name = "idRoom") int idRoom)
	{
		RoomBusyDTO roomBusyDTO = new RoomBusyDTO();
		OrderRoomDTO orderRoomDTO = orderRoomService.getByID(idOrder);
		RoomDTO roomDTO = roomService.getRoomByID(idRoom);
		
		if(orderRoomDTO != null) {
			roomBusyDTO.setOrderRoomDTO(orderRoomDTO);
		}
		roomBusyDTO.setRoomDTO(roomDTO);
		roomBusyDTO.setStatusDTO(statusService.getByID(2));
		roomBusyDTO.setStartDate(orderRoomDTO.getStartDate());
		roomBusyDTO.setEndDate(orderRoomDTO.getEndDate());
		roomBusyDTO.setDaysBooked(0);
		roomBusyDTO.setUnitPrice(0);
		roomBusyService.add(roomBusyDTO);
		
		return "redirect:/admin/rooms-available/idOrder/" + idOrder;
	}
	
	@PostMapping("/selectRooms")
	public String selectRoomForOrder(@ModelAttribute(name = "roomBusyDTO") RoomBusyDTO roomBusyDTO)
	{
		// update roombusy
		float roomPrice = roomBusyDTO.getRoomDTO().getRoomTypeDTO().getPrice();
		float unitPrice = roomBusyDTO.getDaysBooked() * roomPrice;
		roomBusyDTO.setUnitPrice(unitPrice);
		roomBusyService.update(roomBusyDTO);
		
		return "redirect:/admin/rooms-available/idOrder/" ;
	}
	
	// THEM PHONG CHO HOA DON
	@GetMapping("/search-rooms-available/idInvoice/{idInvoice}")
	public String searchRoomsAvailable(Model model,
			@PathVariable(name = "idInvoice") int idInvoice,
			@RequestParam(name = "startDate", required = false) String startDate,
			@RequestParam(name = "endDate", required = false) String endDate)
	{
		InvoiceDTO invoiceDTO = invoiceService.getByID(idInvoice);
		model.addAttribute("startDate", invoiceDTO.getStartDate());
		model.addAttribute("endDate", invoiceDTO.getEndDate());
		model.addAttribute("idInvoice", idInvoice);
		if(startDate != null && endDate != null)
		{
			Timestamp startDateTimestamp = Timestamp.valueOf(LocalDateTime.parse(startDate));
			Timestamp endDateTimestamp = Timestamp.valueOf(LocalDateTime.parse(endDate));
			List<RoomDTO> roomDTOs = 
					roomService.getRoomsAvailableInInvoice(startDateTimestamp, endDateTimestamp);
			model.addAttribute("roomDTOs", roomDTOs);
		}
		return "room/searchRoomAvailable";
	}
	
	// DANH SACH PHONG DAP UNG
	@GetMapping("/get-rooms-available")
	public String getRoomsAvailable(HttpServletRequest request, Model model)
	{
		int idInvoice = Integer.valueOf(request.getParameter("idInvoice"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		return "redirect:/admin/search-rooms-available/idInvoice/" + idInvoice + "?startDate=" 
				+ startDate + "&endDate=" + endDate;
	}
	
	// CHON PHONG VAO HD
	@GetMapping("/select-room-for-invoice")
	public String selectRoomForInvoice(Model model,
			@RequestParam(name = "idRoom") int idRoom,
			@RequestParam(name = "idInvoice") int idInvoice)
	{
		InvoiceDTO invoiceDTO = invoiceService.getByID(idInvoice);
		model.addAttribute("roomBusyDTO", new RoomBusyDTO());
		model.addAttribute("roomDTO", roomService.getRoomByID(idRoom));
		model.addAttribute("idInvoice", idInvoice);
		model.addAttribute("startDate", invoiceDTO.getStartDate());
		model.addAttribute("endDate", invoiceDTO.getEndDate());
		return "room/selectRoomForInvoice";
	}
	
	@PostMapping("/select-room-for-invoice")
	public String selectRoomForInvoice(@ModelAttribute(name = "roomBusyDTO") RoomBusyDTO roomBusyDTO)
	{
		InvoiceDTO invoiceDTO = invoiceService.getByID(roomBusyDTO.getIdInvoice());
		roomBusyDTO.setRoomDTO(roomService.getRoomByID(roomBusyDTO.getIdRoom()));
		roomBusyDTO.setInvoiceDTO(invoiceService.getByID(roomBusyDTO.getIdInvoice()));
		roomBusyDTO.setStatusDTO(statusService.getByID(3));
		
		float unitPrice = roomBusyDTO.getDaysBooked() * roomBusyDTO.getRoomDTO().getRoomTypeDTO().getPrice();
		roomBusyDTO.setUnitPrice(unitPrice);
		roomBusyService.add(roomBusyDTO);
		
		invoiceDTO.setRoomPrice(invoiceDTO.getRoomPrice() + unitPrice);
		invoiceDTO.setQuantityRooms(roomBusyService.getByIdInvoice(invoiceDTO.getId()).size());
		invoiceService.update(invoiceDTO);
		return "redirect:/admin/info-invoice/" + invoiceDTO.getId();
	}
	
//	ADD ROOM
	@GetMapping("/addRoom")
	public String addRoom(Model model, HttpSession session,
			@RequestParam(name = "error", required = false) String error)
	{
		if(error != null) {
			model.addAttribute("error", error);
		}
		
		List<RoomTypeDTO> roomTypeDTOs = roomTypeService.getAll();
		List<HousingDTO> housingDTOs = housingService.getAll();
		model.addAttribute("roomDTO", new RoomDTO());
		session.setAttribute("types", roomTypeDTOs);
		session.setAttribute("housings", housingDTOs);
		return "room/addRoom";
	}
	
	@PostMapping("/addRoom")
	public String addRoom(Model model, 
			@Valid RoomDTO roomDTO, BindingResult bindingResult)
	{	
		if(bindingResult.hasErrors()) {
			model.addAttribute("roomDTO", roomDTO);
			return "room/addRoom";
		}
		List<RoomDTO> roomDTOs = roomService.getAllRoom();
		boolean check = false;
		for(RoomDTO roomDTO2: roomDTOs) {
			if(roomDTO.getName().compareToIgnoreCase(roomDTO2.getName()) == 0) {
				check = true;
				break;
			}
		}
		if(check == true) {
			return "redirect:/admin/addRoom?error=existed";
		}

		RoomTypeDTO roomTypeDTO = roomTypeService.getRoomTypeByID(roomDTO.getIdType());
		HousingDTO housingDTO = housingService.getHousingByID(roomDTO.getIdHousing());
		roomDTO.setRoomTypeDTO(roomTypeDTO);
		roomDTO.setHousingDTO(housingDTO);

		roomService.addRoom(roomDTO);
		return "redirect:/admin/room-list";
	}
	
//	UPDATE
	@GetMapping("/updateRoom/{id}")
	public String updateRoom(Model model, 
			@RequestParam(name = "error", required = false) String error,
			@PathVariable(name = "id") int id,
			HttpSession session)
	{
		if(error != null) {
			model.addAttribute("error", error);
		}
		
		RoomDTO roomDTO = roomService.getRoomByID(id);
		if(roomDTO != null) {
			if(roomDTO.getRoomTypeDTO() != null) {
				model.addAttribute("typeDTO", roomDTO.getRoomTypeDTO());				
			}
			if(roomDTO.getHousingDTO() != null) {
				model.addAttribute("housingDTO", roomDTO.getHousingDTO());							
			}
		}
		
		List<RoomTypeDTO> roomTypeDTOs = roomTypeService.getAll();
		List<HousingDTO> housingDTOs = housingService.getAll();
		model.addAttribute("roomDTO", roomDTO);
		session.setAttribute("types", roomTypeDTOs);
		session.setAttribute("housings", housingDTOs);
		return "room/updateRoom";
	}
	
	@PostMapping("/updateRoom")
	public String updateRoom(Model model, 
			@Valid RoomDTO roomDTO, BindingResult bindingResult)
	{		
		if(bindingResult.hasErrors()) {
			model.addAttribute("roomDTO", roomDTO);
			return "room/updateRoom";
		}
		List<RoomDTO> roomDTOs = roomService.getAllRoom();
		boolean check = false;
		for(RoomDTO roomDTO2: roomDTOs) {
			if(roomDTO.getName().compareToIgnoreCase(roomDTO2.getName()) == 0) {
				if(roomDTO.getId() == roomDTO2.getId()) continue;
				check = true;
				break;
			}
		}
		if(check == true) {
			return "redirect:/admin/updateRoom/" + roomDTO.getId() + "?error=existed";
		}

		RoomTypeDTO roomTypeDTO = roomTypeService.getRoomTypeByID(roomDTO.getIdType());
		HousingDTO housingDTO = housingService.getHousingByID(roomDTO.getIdHousing());
		roomDTO.setRoomTypeDTO(roomTypeDTO);
		roomDTO.setHousingDTO(housingDTO);
		roomService.updateRoom(roomDTO);
		return "redirect:/admin/room-list";
	}
	
	@PostMapping("/delete-room/{id}")
	public String updateRoom(Model model, 
			@PathVariable(name = "id") int id)
	{
		// Kiem tra trong phong ban
		List<RoomBusyDTO> roomBusyDTOs = roomBusyService.getByIdRoom(id);
		if(roomBusyDTOs.size() > 0) {
			model.addAttribute("roomDTO", roomService.getRoomByID(id));
			model.addAttribute("size", roomBusyDTOs.size());
			return "room/cannotDeleteRoom";
		}
		
		// Neu ko co phong ban thi cho xoa
		else if(roomBusyDTOs.size() <= 0) {
			roomService.deleteRoom(id);
		}
		return "redirect:/admin/room-list";
	}
	
//	INFO
	@GetMapping("/info-room/{id}")
	public String infoRoom(Model model,
			@PathVariable(name = "id") int id)
	{
		RoomDTO roomDTO = roomService.getRoomByID(id);
		model.addAttribute("roomDTO", roomDTO);
		return "room/infoRoom";
	}
	
}
