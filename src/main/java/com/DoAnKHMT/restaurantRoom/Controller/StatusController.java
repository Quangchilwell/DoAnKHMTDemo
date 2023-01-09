package com.DoAnKHMT.restaurantRoom.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DoAnKHMT.restaurantRoom.Dao.StatusDao;
import com.DoAnKHMT.restaurantRoom.Model.StatusDTO;
import com.DoAnKHMT.restaurantRoom.Service.StatusService;

@Controller
@RequestMapping("/admin")
public class StatusController {
	
	@Autowired
	StatusDao statusDao;
	
	@Autowired 
	StatusService statusService;
	
	@GetMapping("/status-list")
	public String statusList(Model model)
	{
		List<StatusDTO> statusDTOs = statusService.getAll();
		int recordsInBinNumber = statusDao.recordsInBin();
		model.addAttribute("statusDTOs", statusDTOs);
		model.addAttribute("recordsInBinNumber", statusDao.recordsInBin());
		model.addAttribute("size", statusDTOs.size());
		return "status/statusList";
	}
	
	@GetMapping("/status-bin")
	public String statusBin(Model model)
	{
		List<StatusDTO> statusDTOs = statusService.getAllInBin();
		model.addAttribute("statusDTOs", statusDTOs);
		model.addAttribute("size", statusDTOs.size());
		return "status/statusBin";
	}
	
	
	@GetMapping("/add-status")
	public String addStatus(Model model)
	{
		model.addAttribute("statusDTO", new StatusDTO());
		return "status/addStatus";
	}
	
	@PostMapping("/add-status")
	public String addStatus(Model model,
			@Valid StatusDTO statusDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("statusDTO", statusDTO);
			return "status/addStatus";
		}
		statusService.addStatus(statusDTO);
		return "redirect:/admin/status-list";
	}
	
	@GetMapping("/update-status/{id}")
	public String updateStatus(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("statusDTO", statusService.getByID(id));
		return "status/updateStatus";
	}
	
	@PostMapping("/update-status")
	public String updateStatus(@ModelAttribute(name = "statusDTO") StatusDTO statusDTO)
	{
		statusService.updateStatus(statusDTO);
		return "redirect:/admin/status-list";
	}
	
	// [POST] restore-status
	@PostMapping("/restore-status/{id}")
	public String restoreStatus(@PathVariable(name = "id") int id)
	{
		statusService.restoreStatus(id);
		return "redirect:/admin/status-bin";
	}
	
	
	@PostMapping("/soft-delete-status/{id}")
	public String deleteStatus(@PathVariable(name = "id") int id)
	{
		statusService.softDelete(id);
		return "redirect:/admin/status-list";
	}
	
	@PostMapping("/action-all-status")
	public String softDeleteAllStatus(@RequestParam(name = "action") String action, 
			@RequestParam(name = "idRecords[]", required = false) List<Integer> idList)
	{
		switch (action) {
		case "soft-delete": {
			if(idList == null) {
				return "redirect:/admin/status-list";
			}
			for(int id: idList)
			{
				statusService.softDelete(id);
			}
			break;
		}
		
		case "restore": {
			if(idList == null) {
				return "redirect:/admin/status-bin";
			}
			for(int id: idList)
			{
				statusService.restoreStatus(id);
			}
			return "redirect:/admin/status-bin";
		}
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
		
		return "redirect:/admin/status-list";
	}
	
	@PostMapping("/force-delete-status/{id}")
	public String forceDeleteStatus(@PathVariable(name = "id") int id)
	{
		statusService.deleteStatus(id);
		return "redirect:/admin/status-bin";
	}
}
