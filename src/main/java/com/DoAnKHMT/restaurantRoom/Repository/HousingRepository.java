package com.DoAnKHMT.restaurantRoom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnKHMT.restaurantRoom.Entity.Housing;

public interface HousingRepository extends JpaRepository<Housing, Integer>{
	public Housing findByid(int id);
}
