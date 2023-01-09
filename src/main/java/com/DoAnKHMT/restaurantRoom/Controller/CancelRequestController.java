package com.DoAnKHMT.restaurantRoom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.DoAnKHMT.restaurantRoom.Service.CancelRequestService;

@Controller
@RequestMapping("/admin")
public class CancelRequestController {
	
	@Autowired
	CancelRequestService cancelRequestService;
	
	@GetMapping("/cancel-request-list")
	public String cancelRequestList(Model model)
	{
		model.addAttribute("cancelDTOs", cancelRequestService.getAll());
		return "cancelRequest/cancelRequestList";
	}
}
