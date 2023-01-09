package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.ManagerDao;
import com.DoAnKHMT.restaurantRoom.Entity.Manager;
import com.DoAnKHMT.restaurantRoom.Model.ManagerDTO;
import com.DoAnKHMT.restaurantRoom.Service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{

	@Autowired ManagerDao managerDao;
	
	private void getInfoManagerDTO(ManagerDTO managerDTO, Manager manager) {
		managerDTO.setId(manager.getId());
		managerDTO.setUsername(manager.getUserName());
		managerDTO.setPassword(manager.getPassWord());
		managerDTO.setRole(manager.getRole());
		managerDTO.setDescription(manager.getDescription());
	}
	
	@Override
	public List<ManagerDTO> getAll() {
		List<ManagerDTO> managerDTOs = new ArrayList<ManagerDTO>();
		List<Manager> managers = managerDao.getAll();
		
		for(Manager manager: managers) {
			ManagerDTO managerDTO = new ManagerDTO();
			getInfoManagerDTO(managerDTO, manager);
			managerDTOs.add(managerDTO);
		}
		
		return managerDTOs;
	}

	@Override
	public void add(ManagerDTO managerDTO) {
		Manager manager = new Manager();
		manager.setUserName(managerDTO.getUsername());
		manager.setPassWord(managerDTO.getPassword());
		manager.setRole(managerDTO.getRole());
		manager.setDescription(managerDTO.getDescription());
		managerDao.add(manager);
	}

	@Override
	public void update(ManagerDTO managerDTO) {		
		Manager manager = managerDao.getByID(managerDTO.getId());
		if(manager != null) {
			manager.setUserName(managerDTO.getUsername());
			manager.setPassWord(managerDTO.getPassword());
			manager.setRole(managerDTO.getRole());
			manager.setDescription(managerDTO.getDescription());
			managerDao.update(manager);
		}
	}

	@Override
	public void delete(int id) {
		Manager manager = managerDao.getByID(id);
		if(manager != null) {
			managerDao.delete(manager);
		}
	}

	@Override
	public ManagerDTO getByID(int id) {
		Manager manager = managerDao.getByID(id);
		if(manager != null) {
			ManagerDTO managerDTO = new ManagerDTO();
			getInfoManagerDTO(managerDTO, manager);
			return managerDTO;
		}
		return null;
	}

	@Override
	public ManagerDTO getByUsername(String username) {
		Manager manager = managerDao.getByUsername(username);
		if(manager != null) {
			ManagerDTO managerDTO = new ManagerDTO();
			getInfoManagerDTO(managerDTO, manager);
			return managerDTO;
		}
		return null;
	}

}
