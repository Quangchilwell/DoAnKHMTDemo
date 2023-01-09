package com.DoAnKHMT.restaurantRoom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnKHMT.restaurantRoom.Entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{
	public Manager findByid(int id);
	
	public Manager findByuserName(String username);
}
