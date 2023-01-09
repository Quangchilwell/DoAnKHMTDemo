package com.DoAnKHMT.restaurantRoom.Controller;

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

import com.DoAnKHMT.restaurantRoom.Model.InvoiceDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomBusyDTO;
import com.DoAnKHMT.restaurantRoom.Service.InvoiceService;
import com.DoAnKHMT.restaurantRoom.Service.OrderRoomService;
import com.DoAnKHMT.restaurantRoom.Service.RoomBusyService;
import com.DoAnKHMT.restaurantRoom.Service.RoomService;
import com.DoAnKHMT.restaurantRoom.Service.StatusService;

@Controller
@RequestMapping("/admin")
public class RoomBusyController {
	
	@Autowired
	RoomBusyService roomBusyService;
	
	@Autowired RoomService roomService;
	
	@Autowired StatusService statusService;
	
	@Autowired OrderRoomService orderRoomService;
	
	@Autowired InvoiceService invoiceService;
	
	@GetMapping("/roomBusy-list")
	public String roomBusyList(Model model)
	{
		model.addAttribute("roomBusyDTOs", roomBusyService.getAll());
		return "roomBusy/roomBusyList";
	}
	
//	INFO
	@GetMapping("/info-roomBusy/{id}")
	public String infoRoomBusy(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("roomBusy", roomBusyService.getByID(id));
		return "roomBusy/infoRoomBusy";
	}
	
//	ROOM CALENDAR
	@GetMapping("/calendar-roomBusy/{idRoom}")
	public String roomBusyCalendar(Model model, @PathVariable(name = "idRoom") int idRoom)
	{
		model.addAttribute("roomBusyDTOs", roomBusyService.getByIdRoom(idRoom));
		model.addAttribute("size", roomBusyService.getByIdRoom(idRoom).size());
		model.addAttribute("roomDTO", roomService.getRoomByID(idRoom));
		return "roomBusy/roomBusyCalendar";
	}
	
//	ADD IN ORDER
	@GetMapping("/add-roomBusy")
	public String addRoomBusy(Model model)
	{
		model.addAttribute("roomBusyDTO", new RoomBusyDTO());
		model.addAttribute("roomDTOs", roomService.getAllRoom());
		model.addAttribute("statusDTOs", statusService.getAll());
		return "roomBusy/addRoomBusy";
	}
	
	@PostMapping("/add-roomBusy")
	public String addRoomBusy(@ModelAttribute(name = "roomBusyDTO") RoomBusyDTO roomBusyDTO)
	{
		roomBusyDTO.setRoomDTO(roomService.getRoomByID(roomBusyDTO.getIdRoom()));
		roomBusyDTO.setStatusDTO(statusService.getByID(roomBusyDTO.getIdStatus()));
		roomBusyService.add(roomBusyDTO);
		return "redirect:/admin/roomBusy-list";
	}
	
//	ADD IN INVOICE
	@GetMapping("/add-roomBusy-in-invoice")
	public String addRoomBusyInInvoice(Model model)
	{
//		model.addAttribute("roomBusyDTO", new RoomBusyDTO());
//		model.addAttribute("roomDTOs", roomService.getAllRoom());
//		model.addAttribute("statusDTOs", statusService.getAll());
		return "roomBusy/addRoomBusyInInvoice";
	}
	
//	UPDATE IN ORDER
	@GetMapping("/update-roomBusy/{id}")
	public String updateRoomBusy(Model model, @PathVariable(name = "id") int id)
	{
		RoomBusyDTO roomBusyDTO = roomBusyService.getByID(id);
		model.addAttribute("currentRoom", roomService.getRoomByID(roomBusyDTO.getRoomDTO().getId()));
		model.addAttribute("currentStatus", statusService.getByID(roomBusyDTO.getStatusDTO().getId()));
		model.addAttribute("roomDTOs", roomService.getAllRoom());
		model.addAttribute("statusDTOs", statusService.getAll());
		if(roomBusyDTO.getOrderRoomDTO() != null) {
			model.addAttribute("order", orderRoomService.getByID(roomBusyDTO.getOrderRoomDTO().getId()));
		}
		
		roomBusyDTO.setIdOrder(roomBusyDTO.getOrderRoomDTO().getId());

		model.addAttribute("roomBusyDTO", roomBusyDTO);
		return "roomBusy/updateRoomBusy";
	}
	
	@PostMapping("/update-roomBusy")
	public String updateRoomBusy(@ModelAttribute(name = "roomBusyDTO") RoomBusyDTO roomBusyDTO)
	{
		roomBusyDTO.setRoomDTO(roomService.getRoomByID(roomBusyDTO.getIdRoom()));
		roomBusyDTO.setStatusDTO(statusService.getByID(roomBusyDTO.getIdStatus()));
		roomBusyDTO.setOrderRoomDTO(orderRoomService.getByID(roomBusyDTO.getIdOrder()));
		
		float roomPrice = roomBusyDTO.getRoomDTO().getRoomTypeDTO().getPrice();
		float unitPrice = roomBusyDTO.getDaysBooked() * roomPrice;
		roomBusyDTO.setUnitPrice(unitPrice);
		roomBusyService.update(roomBusyDTO);
		return "redirect:/admin/info-orderRoom/" + roomBusyDTO.getIdOrder();
	}
	
//	UPDATE IN INVOICE
	@GetMapping("/update-roomBusy-in-invoice/{id}")
	public String updateRoomBusyInvoice(Model model, @PathVariable(name = "id") int id)
	{
		RoomBusyDTO roomBusyDTO = roomBusyService.getByID(id);
		model.addAttribute("currentRoom", roomService.getRoomByID(roomBusyDTO.getRoomDTO().getId()));
		model.addAttribute("currentStatus", statusService.getByID(roomBusyDTO.getStatusDTO().getId()));
		model.addAttribute("roomDTOs", roomService.getAllRoom());

		if(roomBusyDTO.getInvoiceDTO() != null) {
			model.addAttribute("invoice", invoiceService.getByID(roomBusyDTO.getInvoiceDTO().getId()));
		}
		
		roomBusyDTO.setIdInvoice(roomBusyDTO.getInvoiceDTO().getId());

		model.addAttribute("roomBusyDTO", roomBusyDTO);
		return "roomBusy/updateRbInInvoice";
	}
	
	@PostMapping("/update-roomBusy-in-invoice")
	public String updateRoomBusyInInvoice(Model model, @Valid RoomBusyDTO roomBusyDTO,
			BindingResult bindingResult)
	{
		InvoiceDTO invoiceDTO = invoiceService.getByID(roomBusyDTO.getIdInvoice());
		roomBusyDTO.setRoomDTO(roomService.getRoomByID(roomBusyDTO.getIdRoom()));
		roomBusyDTO.setStatusDTO(statusService.getByID(roomBusyDTO.getIdStatus()));

		if(bindingResult.hasErrors()) {
			model.addAttribute("currentRoom", roomService.getRoomByID(roomBusyDTO.getRoomDTO().getId()));
			model.addAttribute("currentStatus", statusService.getByID(roomBusyDTO.getStatusDTO().getId()));
			model.addAttribute("roomDTOs", roomService.getAllRoom());
			model.addAttribute("roomBusyDTO", roomBusyDTO);
			return "roomBusy/updateRbInInvoice";
		}
		
		
		float oldRoomPrice = roomBusyDTO.getUnitPrice();
		float newUnitPrice = roomBusyDTO.getRoomDTO().getRoomTypeDTO().getPrice() * roomBusyDTO.getDaysBooked();
		float roomPrice = invoiceDTO.getRoomPrice() - oldRoomPrice + newUnitPrice;
		
		roomBusyDTO.setUnitPrice(newUnitPrice);
		roomBusyService.update(roomBusyDTO);
		
		invoiceDTO.setRoomPrice(roomPrice);
		invoiceService.update(invoiceDTO);
		return "redirect:/admin/info-invoice/" + invoiceDTO.getId();
	}
	
//	DELETE IN ORDER
	@PostMapping("/delete-roomBusy/{id}")
	public String deleteRoomBusy(Model model, @PathVariable(name = "id") int id)
	{
		RoomBusyDTO roomBusyDTO = roomBusyService.getByID(id);
		int idOrder = roomBusyDTO.getOrderRoomDTO().getId();
		roomBusyService.delete(id);
		return "redirect:/admin/info-orderRoom/" + idOrder;
	}
	
//	DELETE IN INVOICE
	@GetMapping("/delete-roomBusy-in-invoice/{id}")
	public String deleteRoomBusyInInvoice(Model model, @PathVariable(name = "id") int id)
	{
		RoomBusyDTO roomBusyDTO = roomBusyService.getByID(id);
		int idInvoice = roomBusyDTO.getInvoiceDTO().getId();
		model.addAttribute("roomBusy", roomBusyDTO);
		model.addAttribute("idInvoice", idInvoice);
		return "roomBusy/deleteRoomBusy";
	}
	
	@GetMapping("/delete-roomBusy-in-invoice-success")
	public String deleteRoomBusySuccess(Model model, @RequestParam(name = "id") int id)
	{
		RoomBusyDTO roomBusyDTO = roomBusyService.getByID(id);
		InvoiceDTO invoiceDTO = invoiceService.getByID(roomBusyDTO.getInvoiceDTO().getId());
		float newRoomPrice = invoiceDTO.getRoomPrice() - roomBusyDTO.getUnitPrice();
		
		// Xoa phong ban
		roomBusyService.delete(id);
		
		// Cap nhat lai hoa don
		invoiceDTO.setRoomPrice(newRoomPrice);
		invoiceDTO.setQuantityRooms(roomBusyService.getByIdInvoice(invoiceDTO.getId()).size());
		invoiceService.update(invoiceDTO);
		return "redirect:/admin/info-invoice/" + invoiceDTO.getId();
	}
	
}
