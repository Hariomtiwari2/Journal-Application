package com.HariomProject.journalApp.Controller;
import com.HariomProject.journalApp.ApiResponse.WeatherResponse;
import com.HariomProject.journalApp.Repository.UserEntryRepository;
import com.HariomProject.journalApp.Services.UserEntryService;
import com.HariomProject.journalApp.Services.WeatherService;
import com.HariomProject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryControllerv2 {

	@Autowired
	private UserEntryService userEntryService;
	@Autowired
	private UserEntryRepository userEntryRepository;
	@Autowired
	private WeatherService weatherService;
	//WeatherResponse weatherResponse;


	//  to update use Put mapping
	@PutMapping
	@RequestMapping("upd")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User userInDb = userEntryService.findByUserName(userName);
		userInDb.setUserName(user.getUserName());
		userInDb.setPassword(user.getPassword());
		userEntryService.saveNewUser(userInDb);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/g")
	public ResponseEntity<?> greetUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		WeatherResponse weatherResponse = weatherService.getweather("Noida");
		String greeting = "";
		if (weatherResponse!=null){
			greeting = " today's temperature is "+weatherResponse.getCurrent().getFeelslike();
		}
		return new ResponseEntity<>("Hi "+ authentication.getName()+greeting,HttpStatus.OK);
	}

}
