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

import com.DoAnKHMT.restaurantRoom.Model.IntrodutionDTO;
import com.DoAnKHMT.restaurantRoom.Model.OrderRoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomTypeDTO;
import com.DoAnKHMT.restaurantRoom.Service.IntrodutionService;
import com.DoAnKHMT.restaurantRoom.Service.OrderRoomService;
import com.DoAnKHMT.restaurantRoom.Service.RoomTypeService;

@Controller
@RequestMapping("/MQ")
public class WebController {
	
	@Autowired
	OrderRoomService orderRoomService;
	
	@Autowired IntrodutionService introdutionService;
	
	@Autowired RoomTypeService roomTypeService;
	
	
	@GetMapping("/home")
	public String home(Model model, HttpSession session)
	{
		IntrodutionDTO introdutionDTO = introdutionService.getIntro(1);
		if(introdutionDTO != null) {
			session.setAttribute("intro", introdutionDTO);
		}
		model.addAttribute("orderRoomDTO", new OrderRoomDTO());
		return "website/home";
	}
	
//	Khach hang gui yeu cau dat don
	@PostMapping("/customer-order")
	public String orderFromCustomer(Model model, @Valid OrderRoomDTO orderRoomDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			return "website/orderFailed";
		}
		orderRoomService.add(orderRoomDTO);
		return "redirect:/MQ/order-success";
	}
	
//	Danh muc phong
	@GetMapping("/room-catergories")
	public String roomCatergories(Model model)
	{
		List<RoomTypeDTO> roomTypeDTOs = roomTypeService.getAll();
		model.addAttribute("roomTypeDTOs", roomTypeDTOs);
		return "website/roomCatergories";
	}
	
//	Chi tiet phong
	@GetMapping("/room-catergories/room-detail/{id}")
	public String roomCatergories(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("type", roomTypeService.getRoomTypeByID(id));
		model.addAttribute("orderRoomDTO", new OrderRoomDTO());
		return "website/roomDetail";
	}
	
//	Xac nhan dat phong thanh cong tu phia khach hang
	@GetMapping("order-success")
	public String orderSuccess(Model model)
	{
		return "website/orderSuccess";
	}
	

	
}
