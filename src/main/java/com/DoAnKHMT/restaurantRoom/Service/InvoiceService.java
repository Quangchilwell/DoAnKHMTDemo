package com.DoAnKHMT.restaurantRoom.Service;

import java.util.List;
import com.DoAnKHMT.restaurantRoom.Model.InvoiceDTO;

public interface InvoiceService {
	public List<InvoiceDTO> getAll();
	
	public List<InvoiceDTO> getCompletionInvoice();
	
	public List<InvoiceDTO> getInvoiceBySearching(String info);
	
	public List<InvoiceDTO> getInvoiceCompletionBySearching(String info);
	
	public void add(InvoiceDTO invoiceDTO);
	
	public void update(InvoiceDTO invoiceDTO);
	
	public void delete(int id);
	
	public InvoiceDTO getByID(int id);
	
}
