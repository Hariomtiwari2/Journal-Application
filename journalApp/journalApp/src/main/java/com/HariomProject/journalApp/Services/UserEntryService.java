package com.HariomProject.journalApp.Services;


// Controller --> Service --> Repository

import com.HariomProject.journalApp.Repository.UserEntryRepository;
import com.HariomProject.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserEntryService {

	@Autowired
	private UserEntryRepository userEntryRepository;



//	public void saveEntry(User user){
//		userEntryRepository.save(user);
//
//	}


//	private static final Logger logger = LoggerFactory.getLogger(UserEntryService.class);
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	// End point
	public boolean saveNewUser(User user){
		try{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList("USER"));
			userEntryRepository.save(user);
			return true;
		}catch (Exception e){
//			logger.info("Hahhahahahaahah");
//			log.error("error occured for user {} " ,user.getUserName(), e);
			log.error("hahahah");
			log.debug("hahaha");
			log.warn("hahaha");
			log.info("hahaha");
			return false;
		}
	}
//	public void saveNewUser(User user){
//		//try{
//			user.setPassword(passwordEncoder.encode(user.getPassword()));
//			user.setRoles(Arrays.asList("USER"));
//			userEntryRepository.save(user);
//
//		//}catch (Exception e){
//		//	logger.info("Hahhahahahaahah");
//
//		//}
//	}

	public User saveAdmin(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER","ADMIN"));
		User user1 = userEntryRepository.save(user);
		return user1;
	}

	public void saveUser(User user){
		userEntryRepository.save(user);
	}
	public List<User> getAll(){
		return userEntryRepository.findAll();
	}

	public Optional<User> findById(ObjectId id){
		return userEntryRepository.findById(id);
	}

	// method to delete mapping
	public void deleteJournalByid(ObjectId id){
		userEntryRepository.deleteById(id);
	}

	public User findByUserName(String userName){
		return userEntryRepository.findByUserName(userName);
	}


}
