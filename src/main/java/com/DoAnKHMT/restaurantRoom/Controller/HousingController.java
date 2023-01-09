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

import com.DoAnKHMT.restaurantRoom.Model.HousingDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomDTO;
import com.DoAnKHMT.restaurantRoom.Service.HousingService;
import com.DoAnKHMT.restaurantRoom.Service.RoomService;

@Controller
@RequestMapping("/admin")
public class HousingController {
	
	@Autowired
	HousingService housingService;
	
	@Autowired
	RoomService roomService;
	
	@GetMapping("/housing-list")
	public String housingList(Model model)
	{
		model.addAttribute("housingDTOs", housingService.getAll());
		return "housing/housingList";
	}
	
	@GetMapping("/addHousing")
	public String addHousing(Model model)
	{
		model.addAttribute("housingDTO", new HousingDTO());
		return "housing/addHousing";
	}
	
	@PostMapping("/addHousing")
	public String addHousing(Model model, @Valid HousingDTO housingDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("housingDTO", housingDTO);
			return "housing/addHousing";
		}
		housingService.addHousing(housingDTO);
		return "redirect:/admin/housing-list";
	}
	
	@GetMapping("/updateHousing/{id}")
	public String updateHousing(Model model, 
			@PathVariable(name = "id") int id,
			@RequestParam(name = "error", required = false) String error)
	{
		if(error != null) {
			model.addAttribute("error", error);
		}
		model.addAttribute("housingDTO", housingService.getHousingByID(id));
		return "housing/updateHousing";
	}
	
	@PostMapping("/updateHousing")
	public String updateHousing(Model model, @Valid HousingDTO housingDTO, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("housingDTO", housingDTO);
			return "housing/updateHousing";
		}
		List<HousingDTO> housingDTOs = housingService.getAll();
		boolean check = false;
		for(HousingDTO housingDTO2: housingDTOs)
		{
			if(housingDTO2.getName().compareToIgnoreCase(housingDTO.getName()) == 0) {
				if(housingDTO2.getId() == housingDTO.getId()) continue;
				check = true;
				break;
			}
		}
		if(check == true) {
			return "redirect:/admin/updateHousing/" +
					housingDTO.getId() + "?error=existed";
		}
		housingService.updateHousing(housingDTO);
		return "redirect:/admin/housing-list";
	}
	
//	DELETE
	@PostMapping("/delete-housing/{id}")
	public String deleteHousing(Model model, @PathVariable(name = "id") int id)
	{
		// Cap nhat khu nha cho phong
		List<RoomDTO> roomDTOs = roomService.getByHousing(id);
		for(RoomDTO roomDTO: roomDTOs) {
			roomDTO.setHousingDTO(null);
			roomService.updateRoom(roomDTO);
		}
		
		// Xoa khu nha
		housingService.deleteHousing(id);
		
		return "redirect:/admin/housing-list";
	}
	
//	INFO
	@GetMapping("/info-housing/{id}")
	public String updateHousing(Model model, 
			@PathVariable(name = "id") int id)
	{
		HousingDTO housingDTO = housingService.getHousingByID(id);
		List<RoomDTO> roomDTOs = roomService.getByHousing(id);
		model.addAttribute("roomsQuantity", roomDTOs.size());
		model.addAttribute("housingDTO", housingDTO);
		return "housing/infoHousing";
	}
	
//	ROOM IN HOUSING
	@GetMapping("/rooms-in-housing/{id}")
	public String roomInHousing(Model model, 
			@PathVariable(name = "id") int id)
	{
		List<RoomDTO> roomDTOs = roomService.getByHousing(id);
		model.addAttribute("housingDTO", housingService.getHousingByID(id));
		model.addAttribute("roomDTOs", roomDTOs);
		return "housing/roomInHousing";
	}
	
}
