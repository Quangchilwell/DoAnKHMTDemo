package com.DoAnKHMT.restaurantRoom.Controller;

import java.util.ArrayList;
import java.util.List;

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

import com.DoAnKHMT.restaurantRoom.Model.InvoiceDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomBusyDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.ServiceInRoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.UseRoomSeviceDTO;
import com.DoAnKHMT.restaurantRoom.Service.InvoiceService;
import com.DoAnKHMT.restaurantRoom.Service.RoomBusyService;
import com.DoAnKHMT.restaurantRoom.Service.RoomService;
import com.DoAnKHMT.restaurantRoom.Service.ServiceInRoomService;
import com.DoAnKHMT.restaurantRoom.Service.UseRoomServiceService;

@Controller
@RequestMapping("/admin")
public class UseRoomServiceController {
	
	public static String add = "ADD";
	public static String update = "UPDATE";
	
	@Autowired UseRoomServiceService useRoomServiceService;
	
	@Autowired ServiceInRoomService serviceInRoomService;
	
	@Autowired InvoiceService invoiceService;
	
	@Autowired RoomBusyService roomBusyService;
	
	@Autowired RoomService roomService;
	
	private void useService(UseRoomSeviceDTO useRoomServiceDTO, String select)
	{
		InvoiceDTO invoiceDTO = invoiceService.getByID(useRoomServiceDTO.getIdInvoice());
		ServiceInRoomDTO serviceInRoomDTO = serviceInRoomService.getByID(useRoomServiceDTO.getIdService());
		useRoomServiceDTO.setInvoiceDTO(invoiceDTO);
		useRoomServiceDTO.setServiceInRoomDTO(serviceInRoomDTO);
		
		float servicePrice = invoiceDTO.getServicePrice();
		float unitPrice = useRoomServiceDTO.getServiceInRoomDTO().getPrice() *
				useRoomServiceDTO.getQuantity();
		
		switch (select) {
			case "ADD": {
				servicePrice += unitPrice;
				useRoomServiceDTO.setUnitPrice(unitPrice);
				useRoomServiceService.add(useRoomServiceDTO);
				// Cap nhat lai tong tien trong HD
				invoiceDTO.setServicePrice(servicePrice);
				invoiceService.update(invoiceDTO);
				break;
			}
			case "UPDATE": {
				float oldUnitPrice = useRoomServiceDTO.getUnitPrice();
				servicePrice = servicePrice - oldUnitPrice + unitPrice;
				useRoomServiceDTO.setUnitPrice(unitPrice);
				useRoomServiceService.update(useRoomServiceDTO);
				// Cap nhat lai tong tien trong HD
				invoiceDTO.setServicePrice(servicePrice);
				invoiceService.update(invoiceDTO);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + select);
			}
		
		
	}
	
//	ADD
	@GetMapping("add-useRoomService/idInvoice/{idInvoice}")
	public String addUseRoomService(Model model, @PathVariable(name = "idInvoice") int idInvoice)
	{
		model.addAttribute("idInvoice", invoiceService.getByID(idInvoice).getId());
		
		List<RoomBusyDTO> roomBusyDTOs = roomBusyService.getByIdInvoice(idInvoice);
		List<ServiceInRoomDTO> serviceInRoomDTOs = serviceInRoomService.getAll();
		
		model.addAttribute( "serviceInRoomDTOs", serviceInRoomDTOs);
		model.addAttribute("roomBusyDTOs", roomBusyDTOs);
		
		model.addAttribute("useRoomServiceDTO", new UseRoomSeviceDTO());
		return "useRoomService/addUseRoomService";
	}
	
	@PostMapping("add-useRoomService")
	public String addUseRoomService(Model model, 
			@ModelAttribute(name = "useRoomServiceDTO") UseRoomSeviceDTO useRoomServiceDTO)
	{
		useRoomServiceDTO.setRoomDTO(roomService.getRoomByID(useRoomServiceDTO.getIdRoom()));
		useService(useRoomServiceDTO, add);
		return "redirect:/admin/info-invoice/" + useRoomServiceDTO.getIdInvoice();
	}
	
	
//	UPDATE
	@GetMapping("update-useRoomService")
	public String updateUseRoomService(Model model, 
			@RequestParam(name = "idInvoice") int idInvoice,
			@RequestParam(name = "id") int id)
	{
		InvoiceDTO invoiceDTO = invoiceService.getByID(idInvoice);
		UseRoomSeviceDTO useRoomSeviceDTO = useRoomServiceService.getByID(id);
		List<ServiceInRoomDTO> serviceInRoomDTOs = serviceInRoomService.getAll();
		List<RoomBusyDTO> roomBusyDTOs = roomBusyService.getByIdInvoice(idInvoice);
			
		model.addAttribute("idInvoice", invoiceDTO.getId());
		model.addAttribute("serviceInRoomDTOs", serviceInRoomDTOs);
		model.addAttribute("service", serviceInRoomService.getByID(useRoomSeviceDTO.getServiceInRoomDTO().getId()));
		model.addAttribute("roomBusyDTOs", roomBusyDTOs);
		model.addAttribute("useRoomSeviceDTO", useRoomSeviceDTO);
		
		model.addAttribute("useRoomServiceDTO", useRoomSeviceDTO);
		return "useRoomService/updateUseRoomService";
	}
	
	@PostMapping("update-useRoomService")
	public String updateUseRoomService(Model model, 
			@ModelAttribute(name = "useRoomServiceDTO") UseRoomSeviceDTO useRoomServiceDTO)
	{		
		useRoomServiceDTO.setRoomDTO(roomService.getRoomByID(useRoomServiceDTO.getIdRoom()));
		useService(useRoomServiceDTO, update);
		return "redirect:/admin/info-invoice/" + useRoomServiceDTO.getIdInvoice();
	}
	
	
//	DELETE
	@PostMapping("delete-useRoomService/{id}")
	public String deleteUseRoomService(@PathVariable(name = "id") int id)
	{
		UseRoomSeviceDTO useRoomSeviceDTO = useRoomServiceService.getByID(id);
		InvoiceDTO invoiceDTO = invoiceService.getByID(useRoomServiceService.getByID(id).getInvoiceDTO().getId());
		float newServicePrice = invoiceDTO.getServicePrice() - useRoomSeviceDTO.getUnitPrice();
		
		// Xoa SDDV
		useRoomServiceService.delete(id);
		
		// Cap nhat lai tong gia cua HD
		invoiceDTO.setServicePrice(newServicePrice);
		invoiceService.update(invoiceDTO);
		return "redirect:/admin/info-invoice/" + invoiceDTO.getId();
	}
}
