package com.DoAnKHMT.restaurantRoom.Dao;

import java.util.List;

import com.DoAnKHMT.restaurantRoom.Entity.Invoice;

public interface InvoiceDao {
	public List<Invoice> getAll();
	
	public void add(Invoice invoice);
	
	public void update(Invoice invoice);
	
	public void delete(Invoice invoice);
	
	public Invoice getByID(int id);
	
}
