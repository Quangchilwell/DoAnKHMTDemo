package com.DoAnKHMT.restaurantRoom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnKHMT.restaurantRoom.Entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>{
	public Status findByid(int id);
}
