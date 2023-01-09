package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.CancelRequestDao;
import com.DoAnKHMT.restaurantRoom.Entity.CancelRequest;
import com.DoAnKHMT.restaurantRoom.Model.CancelRequestDTO;
import com.DoAnKHMT.restaurantRoom.Service.CancelRequestService;

@Service
public class CancelRequestServiceImpl implements CancelRequestService{

	@Autowired
	CancelRequestDao cancelRequestDao;
	
	private void getCancelRequestDTO(CancelRequestDTO cancelRequestDTO, CancelRequest cancelRequest) {
		cancelRequestDTO.setId(cancelRequest.getId());
		cancelRequestDTO.setCancelDate(String.valueOf(cancelRequest.getCancelDate()));
		cancelRequestDTO.setCustomerName(cancelRequest.getCustomerName());
		cancelRequestDTO.setPhone(cancelRequest.getPhone());
		cancelRequestDTO.setDeposit(cancelRequest.getDeposit());
		cancelRequestDTO.setDescription(cancelRequest.getDescription());
	}
	
	@Override
	public List<CancelRequestDTO> getAll() {
		List<CancelRequestDTO> cancelRequestDTOs = new ArrayList<CancelRequestDTO>();
		List<CancelRequest> cancelRequests = cancelRequestDao.getAll();
		
		for(CancelRequest cancelRequest: cancelRequests) {
			CancelRequestDTO cancelRequestDTO = new CancelRequestDTO();
			getCancelRequestDTO(cancelRequestDTO, cancelRequest);
			cancelRequestDTOs.add(cancelRequestDTO);
		}
		return cancelRequestDTOs;
	}

	@Override
	public void add(CancelRequestDTO cancelRequestDTO) {
		CancelRequest cancelRequest = new CancelRequest();
		cancelRequest.setCancelDate(Timestamp.valueOf(LocalDateTime.now()));
		cancelRequest.setCustomerName(cancelRequestDTO.getCustomerName());
		cancelRequest.setPhone(cancelRequestDTO.getPhone());
		cancelRequest.setDeposit(cancelRequestDTO.getDeposit());
		cancelRequest.setDescription(cancelRequestDTO.getDescription());
		cancelRequestDao.add(cancelRequest);	
	}

	@Override
	public void update(CancelRequestDTO cancelRequestDTO) {
		CancelRequest cancelRequest = cancelRequestDao.getByID(cancelRequestDTO.getId());
		if(cancelRequest != null) {
			cancelRequest.setCancelDate(Timestamp.valueOf
					(LocalDateTime.parse(cancelRequestDTO.getCancelDate())));
			cancelRequest.setCustomerName(cancelRequestDTO.getCustomerName());
			cancelRequest.setPhone(cancelRequestDTO.getPhone());
			cancelRequest.setDeposit(cancelRequestDTO.getDeposit());
			cancelRequest.setDescription(cancelRequestDTO.getDescription());
			cancelRequestDao.update(cancelRequest);
		}
	}

	@Override
	public void delete(int id) {
		CancelRequest cancelRequest = cancelRequestDao.getByID(id);
		if(cancelRequest != null) {
			cancelRequestDao.delete(cancelRequest);			
		}
		
	}

	@Override
	public CancelRequestDTO getByID(int id) {
		CancelRequest cancelRequest = cancelRequestDao.getByID(id);
		if(cancelRequest != null) {
			CancelRequestDTO cancelRequestDTO = new CancelRequestDTO();
			getCancelRequestDTO(cancelRequestDTO, cancelRequest);
			return cancelRequestDTO;
		}
		return null;
	}

}
