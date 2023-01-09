package com.DoAnKHMT.restaurantRoom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAnKHMT.restaurantRoom.Entity.RoomType;

public interface RoomTypeRepositoy extends JpaRepository<RoomType, Integer>{
	public RoomType findByid(int id);
}
