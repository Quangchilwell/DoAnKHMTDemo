package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.InvoiceDao;
import com.DoAnKHMT.restaurantRoom.Dao.RoomBusyDao;
import com.DoAnKHMT.restaurantRoom.Dao.UseRoomServiceDao;
import com.DoAnKHMT.restaurantRoom.Entity.Invoice;
import com.DoAnKHMT.restaurantRoom.Entity.RoomBusy;
import com.DoAnKHMT.restaurantRoom.Entity.UseRoomService;
import com.DoAnKHMT.restaurantRoom.Model.InvoiceDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomBusyDTO;
import com.DoAnKHMT.restaurantRoom.Model.UseRoomSeviceDTO;
import com.DoAnKHMT.restaurantRoom.Service.InvoiceService;
import com.DoAnKHMT.restaurantRoom.Service.RoomBusyService;
import com.DoAnKHMT.restaurantRoom.Service.UseRoomServiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired InvoiceDao invoiceDao;
	
	@Autowired UseRoomServiceDao useRoomServiceDao;
	
	@Autowired RoomBusyDao roomBusyDao;
	
	private void setInfoForDTO(Invoice invoice, InvoiceDTO invoiceDTO) {
		float totalPrice = invoice.getRoomPrice() + invoice.getServicePrice();
		invoiceDTO.setId(invoice.getId());
		invoiceDTO.setPersonName(invoice.getPersonName());
		invoiceDTO.setPhone(invoice.getPhone());
		invoiceDTO.setQuantityRooms(invoice.getQuantityRooms());
		invoiceDTO.setNumberPeople(invoice.getNumberPeople());
		
		invoiceDTO.setStartDate(String.valueOf(invoice.getStartDate()));
		invoiceDTO.setEndDate(String.valueOf(invoice.getEndDate()));
		invoiceDTO.setInvoiceCreationDate(String.valueOf(invoice.getInvoiceCreationDate()));
		invoiceDTO.setInvoiceCompletionDate(String.valueOf(invoice.getInvoiceCompletionDate()));
		
		invoiceDTO.setDeposit(invoice.getDeposit());
		invoiceDTO.setRoomPrice(invoice.getRoomPrice());
		invoiceDTO.setServicePrice(invoice.getServicePrice());
		invoiceDTO.setTotalPrice(totalPrice);
		invoiceDTO.setDescription(invoice.getDescription());
	}
	
	private void searchInvoice(Invoice invoice, String info, List<InvoiceDTO> invoiceDTOs) {
		if(invoice.getPersonName().contains(info) || invoice.getPhone().equals(info)) {
			InvoiceDTO invoiceDTO = new InvoiceDTO();
			setInfoForDTO(invoice, invoiceDTO);
			invoiceDTOs.add(invoiceDTO);					
		}
	}
	
	@Override
	public List<InvoiceDTO> getAll() {
		List<InvoiceDTO> invoiceDTOs = new ArrayList<InvoiceDTO>();
		List<Invoice> invoices = invoiceDao.getAll();
		
		for(Invoice invoice: invoices)
		{
			if(invoice.getSoftDelete() == 0)
			{
				InvoiceDTO invoiceDTO = new InvoiceDTO();
				setInfoForDTO(invoice, invoiceDTO);
				invoiceDTOs.add(invoiceDTO);
			}
			
		}	
		return invoiceDTOs;
	}
	
	@Override
	public List<InvoiceDTO> getCompletionInvoice() {
		List<InvoiceDTO> invoiceDTOs = new ArrayList<InvoiceDTO>();
		List<Invoice> invoices = invoiceDao.getAll();
		
		for(Invoice invoice: invoices)
		{
			if(invoice.getSoftDelete() == 1)
			{
				InvoiceDTO invoiceDTO = new InvoiceDTO();
				setInfoForDTO(invoice, invoiceDTO);
				invoiceDTOs.add(invoiceDTO);
			}
			
		}	
		return invoiceDTOs;
	}

	@Override
	public void add(InvoiceDTO invoiceDTO) {
		Invoice invoice = new Invoice();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		invoice.setId(invoiceDTO.getId());
		invoice.setPersonName(invoiceDTO.getPersonName());
		invoice.setPhone(invoiceDTO.getPhone());
		invoice.setQuantityRooms(invoiceDTO.getQuantityRooms());
		invoice.setNumberPeople(invoiceDTO.getNumberPeople());
		
		invoice.setStartDate(Timestamp.valueOf(invoiceDTO.getStartDate()));
		invoice.setEndDate(Timestamp.valueOf(invoiceDTO.getEndDate()));
		invoice.setInvoiceCreationDate(Timestamp.valueOf(localDateTime));
//		invoice.setInvoiceCompletionDate(Timestamp.valueOf(invoiceDTO.getInvoiceCompletionDate()));
		
		invoice.setDeposit(invoiceDTO.getDeposit());
		invoice.setRoomPrice(invoiceDTO.getRoomPrice());
		invoice.setServicePrice(0);
		invoice.setTotalPrice(invoiceDTO.getRoomPrice() + invoiceDTO.getServicePrice());
		invoice.setDescription(invoiceDTO.getDescription());
		invoiceDao.add(invoice);
	}

	@Override
	public void update(InvoiceDTO invoiceDTO) {
		Invoice invoice = invoiceDao.getByID(invoiceDTO.getId());
		if(invoice != null) {
			String startDate = invoiceDTO.getStartDate();
			String endDate = invoiceDTO.getEndDate();

			if(invoiceDTO.getStartDate().contains("T") || invoiceDTO.getEndDate().contains("T")) {
				invoice.setStartDate(Timestamp.valueOf(LocalDateTime.parse(startDate)));
				invoice.setEndDate(Timestamp.valueOf(LocalDateTime.parse(endDate)));
			}
			else {
				invoice.setStartDate(Timestamp.valueOf(startDate));
				invoice.setEndDate(Timestamp.valueOf(endDate));
			}
			
			invoice.setPersonName(invoiceDTO.getPersonName());
			invoice.setPhone(invoiceDTO.getPhone());
			invoice.setQuantityRooms(invoiceDTO.getQuantityRooms());
			invoice.setNumberPeople(invoiceDTO.getNumberPeople());
			//invoice.setInvoiceCreationDate(Timestamp.valueOf(invoiceDTO.getInvoiceCreationDate()));
			//invoice.setInvoiceCompletionDate(Timestamp.valueOf(invoiceDTO.getInvoiceCompletionDate()));
			
			invoice.setDeposit(invoiceDTO.getDeposit());
			invoice.setRoomPrice(invoiceDTO.getRoomPrice());
			invoice.setServicePrice(invoiceDTO.getServicePrice());	
			invoice.setTotalPrice(invoiceDTO.getRoomPrice() + invoiceDTO.getServicePrice());
			invoice.setDescription(invoiceDTO.getDescription());
			invoiceDao.update(invoice);
		}
	}

	@Override
	public void delete(int id) {
		Invoice invoice = invoiceDao.getByID(id);
		invoiceDao.delete(invoice);
	}

	@Override
	public InvoiceDTO getByID(int id) {
		Invoice invoice = invoiceDao.getByID(id);
		if(invoice != null) {
			InvoiceDTO invoiceDTO = new InvoiceDTO();
			setInfoForDTO(invoice, invoiceDTO);
			return invoiceDTO;
		}
		return null;
	}

	@Override
	public List<InvoiceDTO> getInvoiceBySearching(String info) {
		List<InvoiceDTO> invoiceDTOs = new ArrayList<InvoiceDTO>();
		List<Invoice> invoices = invoiceDao.getAll();
		
		for(Invoice invoice: invoices)
		{
			if(invoice.getSoftDelete() == 0)
				searchInvoice(invoice, info, invoiceDTOs);
		}	
		return invoiceDTOs;
	}

	@Override
	public List<InvoiceDTO> getInvoiceCompletionBySearching(String info) {
		List<InvoiceDTO> invoiceDTOs = new ArrayList<InvoiceDTO>();
		List<Invoice> invoices = invoiceDao.getAll();
		
		for(Invoice invoice: invoices)
		{
			if(invoice.getSoftDelete() != 0)
				searchInvoice(invoice, info, invoiceDTOs);
			
		}	
		return invoiceDTOs;
	}
	
}
