package com.DoAnKHMT.restaurantRoom.Controller;

import java.util.List;

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

import com.DoAnKHMT.restaurantRoom.Entity.UseRoomService;
import com.DoAnKHMT.restaurantRoom.Model.ServiceInRoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.UseRoomSeviceDTO;
import com.DoAnKHMT.restaurantRoom.Service.ServiceInRoomService;
import com.DoAnKHMT.restaurantRoom.Service.UseRoomServiceService;

@Controller
@RequestMapping("/admin")
public class ServiceInRoomController {

	@Autowired
	ServiceInRoomService serviceInRoomService;
	
	@Autowired
	UseRoomServiceService useRoomServiceService;
	
	@GetMapping("/service-in-room-list")
	public String serviceInRoomList(Model model)
	{
		model.addAttribute("serDTOs", serviceInRoomService.getAll());
		return "serInRoom/serviceInRoomList";
	}
	
	@GetMapping("/addServiceInRoom")
	public String addServiceInRoom(Model model, 
			@RequestParam(name = "error", required = false) String error)
	{
		if(error != null) {
			model.addAttribute("error", error);
		}
		model.addAttribute("serviceInRoomDTO", new ServiceInRoomDTO());
		return "serInRoom/addServiceInRoom";
	}
	
	@PostMapping("/addServiceInRoom")
	public String addServiceInRoom(Model model, @Valid ServiceInRoomDTO serviceInRoomDTO, 
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("serDTO", new ServiceInRoomDTO());
			return "serInRoom/addServiceInRoom";
		}
		List<ServiceInRoomDTO> serviceInRoomDTOs = serviceInRoomService.getAll();
		boolean check = false;
		for(ServiceInRoomDTO ser: serviceInRoomDTOs)
		{
			if(ser.getName().compareToIgnoreCase(serviceInRoomDTO.getName()) == 0) {
				check = true;
				break;
			}
		}
		
		if(check == true) {
			return "redirect:/admin/addServiceInRoom?error=existed";
		}
		
		serviceInRoomService.addServiceInRoom(serviceInRoomDTO);
		return "redirect:/admin/service-in-room-list";
	}
	
	@GetMapping("/updateServiceInRoom/{id}")
	public String updateServiceInRoom(Model model, 
			@PathVariable(name = "id") int id,
			@RequestParam(name = "error", required = false) String error)
	{
		if(error != null) {
			model.addAttribute("error", error);
		}
		model.addAttribute("serviceInRoomDTO", serviceInRoomService.getByID(id));
		return "serInRoom/updateServiceInRoom";
	}
	
	@PostMapping("/updateServiceInRoom")
	public String updateServiceInRoom(Model model, @Valid ServiceInRoomDTO serviceInRoomDTO, 
			BindingResult bindingResult)
	{
		List<ServiceInRoomDTO> serviceInRoomDTOs = serviceInRoomService.getAll();
		boolean check = false;
		for(ServiceInRoomDTO ser: serviceInRoomDTOs)
		{
			if(ser.getName().compareToIgnoreCase(serviceInRoomDTO.getName()) == 0) {
				if(ser.getId() == serviceInRoomDTO.getId()) continue;
				check = true;
				break;
			}
		}
		
		if(check == true) {
			return "redirect:/admin/updateServiceInRoom/" + serviceInRoomDTO.getId() +"?error=existed";
		}
		
		serviceInRoomService.updateServiceInRoom(serviceInRoomDTO);
		return "redirect:/admin/service-in-room-list";
	}
	
	@PostMapping("/delete-service-in-room/{id}")
	public String deleteServiceInRoom(Model model, @PathVariable(name = "id") int id)
	{	
		List<UseRoomSeviceDTO> useRoomSeviceDTOs = useRoomServiceService.getByIdService(id);
		if(useRoomSeviceDTOs.size() > 0) {
			model.addAttribute("size", useRoomSeviceDTOs.size());
			model.addAttribute("serDTO", serviceInRoomService.getByID(id));
			return "serInRoom/cannotDelete";
		}
		
		else if(useRoomSeviceDTOs.size() == 0) {
			serviceInRoomService.deleteServiceInRoom(id);
		}
		return "redirect:/admin/service-in-room-list";
	}
	
	@GetMapping("/searchingList")
	public String searchingList(Model model,
			@RequestParam(name = "infoSearch") String infoSearch )
	{	
		List<ServiceInRoomDTO> serviceInRoomDTOs = serviceInRoomService.getByNameInclude(infoSearch);
		model.addAttribute("serDTOs", serviceInRoomDTOs);
		return "serInRoom/searchSerInRoom";
	}
}
