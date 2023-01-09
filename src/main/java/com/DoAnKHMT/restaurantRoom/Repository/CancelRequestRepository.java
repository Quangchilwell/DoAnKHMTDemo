package com.DoAnKHMT.restaurantRoom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnKHMT.restaurantRoom.Entity.CancelRequest;

public interface CancelRequestRepository extends JpaRepository<CancelRequest, Integer>{
	public CancelRequest findByid(int id);
}
