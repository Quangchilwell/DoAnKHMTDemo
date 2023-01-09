package com.DoAnKHMT.restaurantRoom.Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

import com.DoAnKHMT.restaurantRoom.Dao.InvoiceDao;
import com.DoAnKHMT.restaurantRoom.Entity.Invoice;
import com.DoAnKHMT.restaurantRoom.Model.InvoiceDTO;
import com.DoAnKHMT.restaurantRoom.Model.OrderRoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomBusyDTO;
import com.DoAnKHMT.restaurantRoom.Model.UseRoomSeviceDTO;
import com.DoAnKHMT.restaurantRoom.Service.InvoiceService;
import com.DoAnKHMT.restaurantRoom.Service.OrderRoomService;
import com.DoAnKHMT.restaurantRoom.Service.RoomBusyService;
import com.DoAnKHMT.restaurantRoom.Service.StatusService;
import com.DoAnKHMT.restaurantRoom.Service.UseRoomServiceService;

@Controller
@RequestMapping("/admin")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;
	
	@Autowired 
	InvoiceDao invoiceDao;

	@Autowired
	OrderRoomService orderRoomService;

	@Autowired
	RoomBusyService roomBusyService;

	@Autowired
	StatusService statusService;
	
	@Autowired
	UseRoomServiceService useRoomServiceService;

	@GetMapping("invoice-list")
	public String invoiceList(Model model) {
		model.addAttribute("invoiceDTOs", invoiceService.getAll());
		model.addAttribute("records", invoiceService.getCompletionInvoice().size());
		return "invoice/invoiceList";
	}
	
	@GetMapping("invoice-completion-list")
	public String invoiceCompletionList(Model model) {
		model.addAttribute("invoiceDTOs", invoiceService.getCompletionInvoice());
		model.addAttribute("records", invoiceService.getAll().size());
		return "invoice/invoiceCompletionList";
	}
	
//	TIM KIEM HD CHUA THANH TOAN
	@GetMapping("/invoice-searching")
	public String invoiceSearching(Model model, @RequestParam(name = "info-search-invoice") String info)
	{
		List<InvoiceDTO> invoiceDTOs = invoiceService.getInvoiceBySearching(info);
		model.addAttribute("invoiceDTOs", invoiceDTOs);
		model.addAttribute("size", invoiceDTOs.size());
		return "invoice/invoiceSearching";
	}
	
//	TIM KIEM HD DA THANH TOAN
	@GetMapping("/invoice-completion-searching")
	public String invoiceCompletedSearching(Model model, @RequestParam(name = "info-search-invoice") String info)
	{
		List<InvoiceDTO> invoiceDTOs = invoiceService.getInvoiceCompletionBySearching(info);
		model.addAttribute("invoiceDTOs", invoiceDTOs);
		return "invoice/invoiceCompletedSearching";
	}

	// GET BY ID
	@GetMapping("info-invoice/{id}")
	public String infoInvoice(Model model, @PathVariable(name = "id") int id) {
		InvoiceDTO invoiceDTO = invoiceService.getByID(id);
		model.addAttribute("invoice", invoiceDTO);
		
		// LAY CAC PHONG CO TRONG HD
		List<RoomBusyDTO> roomBusyDTOs = roomBusyService.getByIdInvoice(id);
		model.addAttribute("roomBusyDTOs", roomBusyDTOs);
		
		// LAY CAC DICH VU SD TRONG HD
		List<UseRoomSeviceDTO> useRoomSeviceDTOs = useRoomServiceService.getByIdInvoice(id);
		model.addAttribute("useRoomSeviceDTOs", useRoomSeviceDTOs);

		// LAY SO TIEN PHAI TRA TRONG HD
		float moneyMustPay = invoiceDTO.getTotalPrice() - invoiceDTO.getDeposit();
		model.addAttribute("moneyMustPay", moneyMustPay);
		
		model.addAttribute("startDate", invoiceDTO.getStartDate());
		model.addAttribute("endDate", invoiceDTO.getEndDate());

		return "invoice/infoInvoice";
	}
	
	@GetMapping("info-invoice-completion/{id}")
	public String infoCompletionInvoice(Model model, @PathVariable(name = "id") int id) {
		InvoiceDTO invoiceDTO = invoiceService.getByID(id);
		model.addAttribute("invoice", invoiceDTO);
		return "invoice/infoCompletionInvoice";
	}

	// ADD
	@GetMapping("add-invoice-from-order")
	public String addInvoiceFromOrder(@RequestParam(name = "idOrder") int idOrder) {
		OrderRoomDTO orderRoomDTO = orderRoomService.getByID(idOrder);
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		List<RoomBusyDTO> roomBusyInOrders = roomBusyService.getByIdOrder(idOrder);

		// TAO MOI MOT HOA DON TU MOT DON DAT
		invoiceDTO.setId(idOrder);
		invoiceDTO.setPersonName(orderRoomDTO.getPersonName());
		invoiceDTO.setPhone(orderRoomDTO.getPhone());
		invoiceDTO.setNumberPeople(orderRoomDTO.getNumberPeople());
		invoiceDTO.setQuantityRooms(roomBusyInOrders.size());

		invoiceDTO.setStartDate(orderRoomDTO.getStartDate());
		invoiceDTO.setEndDate(orderRoomDTO.getEndDate());

		invoiceDTO.setDeposit(orderRoomDTO.getDeposit());
		invoiceService.add(invoiceDTO);

		// CAP NHAT PHONG VAO HOA DON, DONG THOI XOA MA DON DAT
		float roomPrice = 0;
		for (RoomBusyDTO roomBusyDTO : roomBusyInOrders) {
			roomPrice += roomBusyDTO.getUnitPrice();
			roomBusyDTO.setInvoiceDTO(invoiceDTO);
			roomBusyDTO.setStatusDTO(statusService.getByID(3));
			roomBusyDTO.setOrderRoomDTO(null);
			roomBusyService.update(roomBusyDTO);
		}

		// CAP NHAT HD DE LAY TONG SO TIEN PHONG TRONG HD
		invoiceDTO.setRoomPrice(roomPrice);
		invoiceService.update(invoiceDTO);
		
		// Xoa don dat di
		orderRoomService.delete(idOrder);

		return "redirect:/admin/invoice-list";
	}
	
//	UPDATE
	@GetMapping("/update-invoice/{id}")
	public String updateInvoice(Model model, @PathVariable(name = "id") int id) {
		InvoiceDTO invoiceDTO = invoiceService.getByID(id);
		model.addAttribute("invoiceDTO", invoiceDTO);
		return "invoice/updateInvoice";
	}
	
	@PostMapping("/update-invoice")
	public String updateInvoice(Model model, @Valid InvoiceDTO invoiceDTO,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("invoiceDTO", invoiceDTO);
			return "invoice/updateInvoice";
		}
		invoiceService.update(invoiceDTO);
		return "redirect:/admin/info-invoice/" + invoiceDTO.getId();
	}
	
//	DELETE 
	@PostMapping("/delete-invoice/{id}")
	public String deleteInvoice(@PathVariable(name = "id") int id) {
		List<RoomBusyDTO> RoomBusyDTOs = roomBusyService.getByIdInvoice(id);
		List<UseRoomSeviceDTO> useRoomSeviceDTOs = useRoomServiceService.getByIdInvoice(id);
		
		for(RoomBusyDTO roomBusyDTO: RoomBusyDTOs) {
			roomBusyService.delete(roomBusyDTO.getId());
		}
		
		for(UseRoomSeviceDTO useRoomSeviceDTO: useRoomSeviceDTOs) {
			useRoomServiceService.delete(useRoomSeviceDTO.getId());
		}
		
		invoiceService.delete(id);
		return "redirect:/admin/invoice-completion-list";
	}
	
	
//	COMPLETION INVOICE
	@GetMapping("/completion-invoice")
	public String completionInvoice(@RequestParam(name = "id") int id)
	{
		// Xoa cac dich vu khoi csdl
		List<UseRoomSeviceDTO> useRoomSeviceDTOs = useRoomServiceService.getByIdInvoice(id);
		for(UseRoomSeviceDTO useRoomSeviceDTO: useRoomSeviceDTOs) {
			useRoomServiceService.delete(useRoomSeviceDTO.getId());
		}
		
		// Xoa cac phong ban khoi csdl
		List<RoomBusyDTO> roomBusyDTOs = roomBusyService.getByIdInvoice(id);
		for(RoomBusyDTO roomBusyDTO: roomBusyDTOs) {
			roomBusyService.delete(roomBusyDTO.getId());
		}
		
		// Dua hoa don vao danh sach hoan thanh
		Invoice invoice = invoiceDao.getByID(id);
		invoice.setSoftDelete(1);
		invoice.setInvoiceCompletionDate(Timestamp.valueOf(LocalDateTime.now()));
		invoiceDao.update(invoice);
		return "redirect:/admin/invoice-list";
	}
}
