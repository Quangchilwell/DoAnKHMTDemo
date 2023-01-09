package com.DoAnKHMT.restaurantRoom.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.InvoiceDao;
import com.DoAnKHMT.restaurantRoom.Entity.Invoice;
import com.DoAnKHMT.restaurantRoom.Repository.InvoiceRepository;

@Repository
public class InvoiceDaoImpl implements InvoiceDao{

	@Autowired InvoiceRepository invoiceRepository;
	
	@Override
	public List<Invoice> getAll() {
		return invoiceRepository.findAll();
	}

	@Override
	public void add(Invoice invoice) {
		invoiceRepository.save(invoice);
	}

	@Override
	public void update(Invoice invoice) {
		invoiceRepository.save(invoice);
	}

	@Override
	public void delete(Invoice invoice) {
		invoiceRepository.delete(invoice);
	}

	@Override
	public Invoice getByID(int id) {
		return invoiceRepository.findByid(id);
	}

}
