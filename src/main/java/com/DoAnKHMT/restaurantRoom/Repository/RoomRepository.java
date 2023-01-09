package com.DoAnKHMT.restaurantRoom.Repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.DoAnKHMT.restaurantRoom.Entity.Housing;
import com.DoAnKHMT.restaurantRoom.Entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{
	public Room findByid(int id);
	
	public List<Room> findRoomByidType(int idType);
	
	public List<Room> findRoomByidHousing(int idHousing);
	
	@Query(value = "SELECT r FROM Room r \r\n"
			+ "WHERE r.id NOT IN \r\n"
			+ "(\r\n"
			+ "    SELECT r.id \r\n"
			+ "    FROM Room r \r\n"
			+ "    WHERE r.id IN (\r\n"
			+ "        SELECT rb.idRoom FROM RoomBusy rb, OrderRoom o \r\n"
			+ "        WHERE o.id = ?1 and o.startDate < rb.endDate\r\n"
			+ "    ) \r\n"
			+ "    GROUP BY r.id\r\n"
			+ ")"
			+ "")
	public List<Room> findRoomsAvailable(int idOrder);
	
	@Query(value = "SELECT r From Room r \r\n"
			+ "WHERE r.id NOT IN (\r\n"
			+ "	SELECT rb.idRoom FROM RoomBusy rb \r\n"
			+ ")\r\n"
			+ "OR r.id NOT IN (\r\n"
			+ "	SELECT rb.idRoom FROM RoomBusy AS rb\r\n"
			+ "    WHERE ?1 >= rb.startDate AND ?1 <= rb.endDate \r\n"
			+ "    OR ?2 >= rb.startDate AND ?2 <= rb.endDate \r\n"
			+ ")")
	public List<Room> findRoomsAvailableFoInvoice(Timestamp startDate, Timestamp endDate);

}
