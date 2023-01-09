package com.DoAnKHMT.restaurantRoom.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.DoAnKHMT.restaurantRoom.Dao.RoomTypeDao;
import com.DoAnKHMT.restaurantRoom.Entity.RoomType;
import com.DoAnKHMT.restaurantRoom.Model.RoomDTO;
import com.DoAnKHMT.restaurantRoom.Model.RoomTypeDTO;
import com.DoAnKHMT.restaurantRoom.Service.RoomService;
import com.DoAnKHMT.restaurantRoom.Service.RoomTypeService;

@Controller
@RequestMapping("/admin")
public class RoomTypeController {

	@Autowired
	RoomTypeService roomTypeService;

	@Autowired
	RoomTypeDao roomTypeDao;

	@Autowired
	RoomService roomService;

	@GetMapping("/room-type-list")
	public String roomTypeList(Model model) {
		List<RoomTypeDTO> roomTypeDTOs = roomTypeService.getAll();
		model.addAttribute("roomTypeDTOs", roomTypeDTOs);
		return "roomType/roomTypeList";
	}

	@GetMapping("/addRoomType")
	public String addRoomType(Model model, @RequestParam(name = "error", required = false) String error) {
		if (error != null) {
			model.addAttribute("error", error);
		}
		model.addAttribute("roomTypeDTO", new RoomTypeDTO());
		return "roomType/addRoomType";
	}

	@PostMapping("/addRoomType")
	public String addRoomType(Model model, @Valid RoomTypeDTO roomTypeDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("roomTypeDTO", roomTypeDTO);
			return "roomType/addRoomType";
		}
		MultipartFile file = roomTypeDTO.getFile();
		try {
			File newFile = new File("E:/SpringBoot/workspace/restaurantRoom/src/main/resources/static/img/imgRoomType/"
					+ file.getOriginalFilename());
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();

			roomTypeDTO.setImage(file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<RoomTypeDTO> roomTypeDTOs = roomTypeService.getAll();
		boolean check = false;
		for (RoomTypeDTO roomTypeDTO2 : roomTypeDTOs) {
			if (roomTypeDTO.getName().compareToIgnoreCase(roomTypeDTO2.getName()) == 0) {
				check = true;
				break;
			}
		}
		if (check == true) {
			return "redirect:/admin/addRoomType?error=existed";
		}
		roomTypeService.addRoomType(roomTypeDTO);
		return "redirect:/admin/room-type-list";
	}

	@GetMapping("/updateRoomType/{id}")
	public String updateRoomType(Model model, @PathVariable(name = "id") int id,
			@RequestParam(name = "error", required = false) String error) {
		if (error != null) {
			model.addAttribute("error", error);
		}
		RoomTypeDTO roomTypeDTO = roomTypeService.getRoomTypeByID(id);
		model.addAttribute("roomTypeDTO", roomTypeDTO);
		return "roomType/updateRoomType";
	}

	@PostMapping("/updateRoomType")
	public String updateRoomType(Model model, @Valid RoomTypeDTO roomTypeDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("roomTypeDTO", roomTypeDTO);
			return "roomType/addRoomType";
		}
		RoomType roomType = roomTypeDao.getRoomTypeByID(roomTypeDTO.getId());
		MultipartFile file = roomTypeDTO.getFile();
		try {
			File newFile = new File("E:/SpringBoot/workspace/restaurantRoom/src/main/resources/static/img/imgRoomType/"
					+ file.getOriginalFilename());
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<RoomTypeDTO> roomTypeDTOs = roomTypeService.getAll();
		boolean check = false;
		for (RoomTypeDTO roomTypeDTO2 : roomTypeDTOs) {
			if (roomTypeDTO.getName().compareToIgnoreCase(roomTypeDTO2.getName()) == 0) {
				if (roomTypeDTO.getId() == roomTypeDTO2.getId()) {
					continue;
				}
				check = true;
				break;
			}
		}
		if (check == true) {
			return "redirect:/admin/updateRoomType/" + roomTypeDTO.getId() + "?error=existed";
		}

		if (file.getOriginalFilename().equals("")) {
			roomTypeDTO.setImage(roomType.getImg());
		} else {
			roomTypeDTO.setImage(file.getOriginalFilename());
		}
		roomTypeService.updateRoomType(roomTypeDTO);
		return "redirect:/admin/room-type-list";
	}

//	DELETE
	@PostMapping("/delete-room-type/{id}")
	public String deleteRoomType(Model model, @PathVariable(name = "id") int id) {
		// Cập nhật lại danh mục cho các phòng
		List<RoomDTO> roomDTOs = roomService.getByRoomType(id);

		for (RoomDTO roomDTO : roomDTOs) {
			roomDTO.setRoomTypeDTO(null);
			roomService.updateRoom(roomDTO);
		}
		roomTypeService.deleteRoomType(id);
		return "redirect:/admin/room-type-list";
	}

	@GetMapping("/info-room-type/{id}")
	public String infoRoomType(Model model, @PathVariable(name = "id") int id) {
		RoomTypeDTO roomTypeDTO = roomTypeService.getRoomTypeByID(id);
		model.addAttribute("typeDTO", roomTypeDTO);
		return "roomType/infoRoomType";
	}

	@GetMapping("/rooms-in-type/{id}")
	public String roomsInType(Model model, @PathVariable(name = "id") int id) {
		List<RoomDTO> roomDTOs = roomService.getByRoomType(id);
		model.addAttribute("roomDTOs", roomDTOs);
		return "roomType/roomsInType";
	}
}
