package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.StatusDao;
import com.DoAnKHMT.restaurantRoom.Entity.Status;
import com.DoAnKHMT.restaurantRoom.Model.StatusDTO;
import com.DoAnKHMT.restaurantRoom.Service.StatusService;

@Service
public class StatusServiceImpl implements StatusService{

	@Autowired
	StatusDao statusDao;
	
	@Override
	public List<StatusDTO> getAll() {
		List<StatusDTO> statusDTOs = new ArrayList<StatusDTO>();
		List<Status> statusList = statusDao.getAll();
		
		for(Status status: statusList)
		{
			// 0: coi la chua xoa mem
			if(status.getSoftDelete() == 0) {
				StatusDTO statusDTO = new StatusDTO();
				statusDTO.setId(status.getId());
				statusDTO.setName(status.getName());
				statusDTO.setDescription(status.getDescription());
				statusDTO.setCreateAt(String.valueOf(status.getCreateAt()));
				statusDTOs.add(statusDTO);				
			}
		}
		
		return statusDTOs;
	}
	
	@Override
	public List<StatusDTO> getAllInBin() {
		List<StatusDTO> statusDTOs = new ArrayList<StatusDTO>();
		List<Status> statusList = statusDao.getAll();
		
		for(Status status: statusList)
		{
			// 1: coi la da xoa mem
			if(status.getSoftDelete() == 1) {
				StatusDTO statusDTO = new StatusDTO();
				statusDTO.setId(status.getId());
				statusDTO.setName(status.getName());
				statusDTO.setDescription(status.getDescription());
				statusDTO.setCreateAt(String.valueOf(status.getCreateAt()));
				statusDTOs.add(statusDTO);				
			}
		}
		
		return statusDTOs;
	}

	@Override
	public void addStatus(StatusDTO statusDTO) {
		LocalDateTime localDateTime = LocalDateTime.now();
		Status status = new Status();
		status.setName(statusDTO.getName());
		status.setDescription(statusDTO.getDescription());
		status.setCreateAt(Timestamp.valueOf(localDateTime));
		
		status.setSoftDelete(0);
		statusDao.addStatus(status);
		
	}

	@Override
	public void updateStatus(StatusDTO statusDTO) {
		LocalDateTime localDateTime = LocalDateTime.now();
		Status status = statusDao.getByID(statusDTO.getId());
		if(status != null) {
			status.setName(statusDTO.getName());
			status.setDescription(statusDTO.getDescription());
			status.setUpdateAt(Timestamp.valueOf(localDateTime));
			statusDao.updateStatus(status);
		}
	}
	
	@Override
	public void restoreStatus(int id) {
		Status status = statusDao.getByID(id);
		if(status != null) {
			status.setSoftDelete(0);
			statusDao.updateStatus(status);
		}
		
	}

	@Override
	public void deleteStatus(int id) {
		Status status = statusDao.getByID(id);
		if(status != null) {
			statusDao.deleteStatus(status);
		}
	}

	@Override
	public StatusDTO getByID(int id) {
		Status status = statusDao.getByID(id);
		if(status != null) {
			StatusDTO statusDTO = new StatusDTO();
			statusDTO.setId(status.getId());
			statusDTO.setName(status.getName());
			statusDTO.setDescription(status.getDescription());
			statusDTO.setCreateAt(String.valueOf(status.getCreateAt()));
			return statusDTO;
		}
		return null;
	}

	@Override
	public void softDelete(int id) {
		Status status = statusDao.getByID(id);
		if(status != null) {
			status.setSoftDelete(1);
			statusDao.updateStatus(status);
		}
	}

}
