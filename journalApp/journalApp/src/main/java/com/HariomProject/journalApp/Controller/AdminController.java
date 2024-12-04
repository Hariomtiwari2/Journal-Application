package com.HariomProject.journalApp.Controller;

import com.HariomProject.journalApp.Services.UserEntryService;
import com.HariomProject.journalApp.cache.AppCache;
import com.HariomProject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserEntryService userEntryService;
	@Autowired
	private AppCache appCache;


	@GetMapping("/getUser")
	public ResponseEntity<?> getUsers(){
		List<User> all =  userEntryService.getAll();
		if (all != null && !all.isEmpty()){
			return new ResponseEntity<>(all, HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/create-admin-user")
	public ResponseEntity createAdmin(@RequestBody User user) {
		User admin = userEntryService.saveAdmin(user);
		return new ResponseEntity(admin , HttpStatus.CREATED);
	}

	@GetMapping("/clear-App-Cache")
	public ResponseEntity clearAppCache(){
		appCache.init();
		return new ResponseEntity(HttpStatus.OK);
	}
}
