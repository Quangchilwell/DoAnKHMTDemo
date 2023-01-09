package com.DoAnKHMT.restaurantRoom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnKHMT.restaurantRoom.Entity.Introdution;

public interface IntrodutionRepository extends JpaRepository<Introdution, Integer>{
	public Introdution findByid(int id);
}
