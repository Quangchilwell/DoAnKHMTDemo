package com.DoAnKHMT.restaurantRoom.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DoAnKHMT.restaurantRoom.Model.ManagerDTO;
import com.DoAnKHMT.restaurantRoom.Service.ManagerService;

@Controller
@RequestMapping("/admin")
public class ManagerController {
	
	@Autowired ManagerService managerService;
	
	@GetMapping("manager-account-list")
	public String managerAccountList(Model model)
	{
		model.addAttribute("managerDTOs", managerService.getAll());
		return "manager/managerAccountList";
	}
	
	@GetMapping("/change-password/{id}")
	public String changePassword(Model model, @PathVariable(name = "id") int id, 
			@RequestParam(name = "error", required = false) String error)
	{
		if(error != null) {
			model.addAttribute("error", error);
		}
		model.addAttribute("manager", managerService.getByID(id));
		return "manager/changePassword";
	}
	
	@PostMapping("/change-password")
	public String changePassword(Model model, HttpServletRequest request,
			@ModelAttribute(name = "manager") ManagerDTO managerDTO)
	{
		ManagerDTO managerDTO2 = managerService.getByID(managerDTO.getId());
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("password");
		
		if(oldPassword.equals(managerDTO2.getPassword())) {
			managerDTO.setPassword(newPassword);
			managerService.update(managerDTO);
			return "redirect:/admin/manager-account-list";
		}
		return "redirect:/admin/change-password/"+ managerDTO.getId() + "?error=error";
	}
}
