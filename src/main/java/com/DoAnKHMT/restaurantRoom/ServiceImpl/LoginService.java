package com.DoAnKHMT.restaurantRoom.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.DoAnKHMT.restaurantRoom.Dao.ManagerDao;
import com.DoAnKHMT.restaurantRoom.Entity.Manager;
import com.DoAnKHMT.restaurantRoom.Model.ManagerDTO;

@Service
public class LoginService implements UserDetailsService{

	@Autowired
	ManagerDao managerDao;
	
	@Autowired
	HttpSession session;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Manager manager = managerDao.getByUsername(username);
		if(manager == null) {
			throw new UsernameNotFoundException("no this admin!");
		}
		
		session.setAttribute("manager", manager);
		
		// Danh sach cac quyen
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(manager.getRole()));
		
		// Thong tin nguoi dang nhap
		UserDetails userDetails = new User(manager.getUserName(), "{noop}" + manager.getPassWord(), 
				true, true, true, true, authorities);
		return userDetails;
	}
	
}
