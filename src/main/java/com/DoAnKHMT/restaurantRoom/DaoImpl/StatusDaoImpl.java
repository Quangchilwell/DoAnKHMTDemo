package com.DoAnKHMT.restaurantRoom.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DoAnKHMT.restaurantRoom.Dao.StatusDao;
import com.DoAnKHMT.restaurantRoom.Entity.Status;
import com.DoAnKHMT.restaurantRoom.Repository.StatusRepository;

@Repository
public class StatusDaoImpl implements StatusDao{

	@Autowired
	StatusRepository statusRepository;
	
	@Override
	public List<Status> getAll() {
		List<Status> status = statusRepository.findAll();
		return status;
	}

	@Override
	public void addStatus(Status status) {
		statusRepository.save(status);
	}

	@Override
	public void updateStatus(Status status) {
		statusRepository.save(status);
	}

	@Override
	public void deleteStatus(Status status) {
		statusRepository.delete(status);
	}

	@Override
	public Status getByID(int id) {
		return statusRepository.findByid(id);
	}

	@Override
	public void softDelete(Status status) {
		status.setSoftDelete(1);
		
	}

	@Override
	public int recordsInBin() {
		int count = 0;
		List<Status> status = statusRepository.findAll();
		for(Status st: status)
		{
			if(st.getSoftDelete() == 1) {
				count += 1;
			}
		}
		return count;
	}

}
