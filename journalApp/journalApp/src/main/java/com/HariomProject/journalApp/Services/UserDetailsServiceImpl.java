package com.HariomProject.journalApp.Services;

import com.HariomProject.journalApp.Repository.UserEntryRepository;
import com.HariomProject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserEntryRepository userEntryRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userEntryRepository.findByUserName(username);
		if (user!= null){
			UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
					.username(user.getUserName())
					.password(user.getPassword())
					.roles(user.getRoles().toArray(new String[0]))
					.build();
			return userDetails;
		}
		throw new UsernameNotFoundException("User not found with username: "+username);

	}
}
