package com.DoAnKHMT.restaurantRoom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnKHMT.restaurantRoom.Entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
	public Invoice findByid(int id);
}
