package com.DoAnKHMT.restaurantRoom.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.DoAnKHMT.restaurantRoom.ServiceImpl.LoginService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	LoginService loginService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers("/MQ/**").permitAll()
			.antMatchers("/css/**", "/js/**", "/img/**").permitAll()
			.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
			.and().formLogin().defaultSuccessUrl("/admin/orderRoom-list");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
//		return bCryptPasswordEncoder;
//	}
}
