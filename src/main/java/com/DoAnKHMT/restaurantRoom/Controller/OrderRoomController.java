package com.DoAnKHMT.restaurantRoom.Controller;

import java.util.List;

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

import com.DoAnKHMT.restaurantRoom.Entity.OrderRoom;
import com.DoAnKHMT.restaurantRoom.Model.CancelRequestDTO;
import com.DoAnKHMT.restaurantRoom.Model.OrderRoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomBusyDTO;
import com.DoAnKHMT.restaurantRoom.Service.CancelRequestService;
import com.DoAnKHMT.restaurantRoom.Service.OrderRoomService;
import com.DoAnKHMT.restaurantRoom.Service.RoomBusyService;

@Controller
@RequestMapping("/admin")
public class OrderRoomController {

	@Autowired
	OrderRoomService orderRoomService;

	@Autowired
	RoomBusyService roomBusyService;
	
	@Autowired
	CancelRequestService cancelRequestService;

	@GetMapping("/orderRoom-list")
	public String orderRoomList(Model model, HttpSession session) {
		List<OrderRoomDTO> orderRoomDTOs = orderRoomService.getOrderAccepted();
		model.addAttribute("orderRoomDTOs", orderRoomService.getAll());
		model.addAttribute("size", orderRoomService.getAll().size());
		model.addAttribute("numberOrderAccepted", orderRoomDTOs.size());
		session.setAttribute("ordersQuantity", orderRoomService.getAll().size());
		return "orderRoom/orderRoomList";
	}
	

	@GetMapping("/orderRooms-accepted")
	public String orderRoomAcceptedList(Model model) {
		model.addAttribute("orderRoomDTOs", orderRoomService.getOrderAccepted());
		model.addAttribute("size", orderRoomService.getOrderAccepted().size());
		return "orderRoom/orderRoomsAccepted";
	}

//	Info 
	@GetMapping("/info-orderRoom/{id}")
	public String infoOrderRoom(Model model, @PathVariable(name = "id") int id, HttpSession session) {
		model.addAttribute("orderRoom", orderRoomService.getByID(id));
		// Danh sach phong dat sap xep trong don
		List<RoomBusyDTO> roomBusyDTOs = roomBusyService.getByIdOrder(id);
		model.addAttribute("roomBusyDTOs", roomBusyDTOs);
		return "orderRoom/infoOrderRoom";
	}
	
	
//	SEARCH
	@GetMapping("/search-order")
	public String OrderRoomSearching(Model model, @RequestParam(name = "info-search") String info) {
		List<OrderRoomDTO> orderRoomDTOs = orderRoomService.getOrderRoomBySearching(info);
		model.addAttribute("orderRoomDTOs", orderRoomDTOs);
		model.addAttribute("size", orderRoomDTOs.size());
//		model.addAttribute("numberOrderAccepted", orderRoomDTOs.size());
		return "orderRoom/OrderRoomSearching";
	}

//	ADD
	@GetMapping("/add-orderRoom")
	public String addOrderRoom(Model model) {
		model.addAttribute("orderRoomDTO", new OrderRoomDTO());
		return "orderRoom/addOrderRoom";
	}

	@PostMapping("/add-orderRoom")
	public String addOrderRoom(Model model, @Valid OrderRoomDTO orderRoomDTO,
			BindingResult bindingResult) 
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("orderRoomDTO", orderRoomDTO);
			return "orderRoom/addOrderRoom";
		}
		orderRoomService.add(orderRoomDTO);
		return "redirect:/admin/orderRoom-list";
	}

//	UPDATE
	@GetMapping("/update-orderRoom/{id}")
	public String updateOrderRoom(Model model, @PathVariable(name = "id") int id) {
		OrderRoomDTO orderRoomDTO = orderRoomService.getByID(id);
		model.addAttribute("orderRoomDTO", orderRoomDTO);
		return "orderRoom/updateOrderRoom";
	}

	@PostMapping("/update-orderRoom")
	public String updateOrderRoom(Model model, @Valid OrderRoomDTO orderRoomDTO,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("orderRoomDTO", orderRoomDTO);
			return "orderRoom/updateOrderRoom";
		}
		orderRoomService.update(orderRoomDTO);
		return "redirect:/admin/orderRoom-list";
	}

	@PostMapping("/accept-orderRoom/{id}")
	public String acceptOrderRoom(@PathVariable(name = "id") int id) {
		OrderRoomDTO orderRoomDTO = orderRoomService.getByID(id);
		orderRoomDTO.setStatus(1);
		orderRoomService.update(orderRoomDTO);
		return "redirect:/admin/orderRoom-list";
	}

//	DELETE
	@PostMapping("/delete-orderRoom/{id}")
	public String deleteOrderRoom(@PathVariable(name = "id") int id) {
		orderRoomService.delete(id);
		return "redirect:/admin/orderRoom-list";
	}
	
	@PostMapping("/cancel-orderRoom/{id}")
	public String cancelOrderRoom(@PathVariable(name = "id") int id) {
		OrderRoomDTO orderRoomDTO = orderRoomService.getByID(id);
		// Xoa cac phong ban trong don
		List<RoomBusyDTO> roomBusyDTOs = roomBusyService.getByIdOrder(id);
		for(RoomBusyDTO roomBusyDTO: roomBusyDTOs) {
			roomBusyService.delete(roomBusyDTO.getId());
		}

		// Cap nhat vao bang huy phong.
		CancelRequestDTO cancelRequestDTO = new CancelRequestDTO();
		cancelRequestDTO.setCustomerName(orderRoomDTO.getPersonName());
		cancelRequestDTO.setPhone(orderRoomDTO.getPhone());
		cancelRequestDTO.setDeposit(orderRoomDTO.getDeposit());
		cancelRequestService.add(cancelRequestDTO);
		
		// Xoa don dat phong
		orderRoomService.delete(id);
		return "redirect:/admin/cancel-request-list";
	}
		
}
