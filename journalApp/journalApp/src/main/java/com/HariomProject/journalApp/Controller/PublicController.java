package com.HariomProject.journalApp.Controller;
import com.HariomProject.journalApp.Services.UserEntryService;
import com.HariomProject.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")



public class PublicController {
	@GetMapping("/HealthEntry-check")
	public String healthCheck(){
		return "API is Working";
	}

	@Autowired
	private UserEntryService userEntryService;

	@PostMapping("/createUser")
	public void createUsers(@RequestBody User user){
		userEntryService.saveNewUser(user);
	}
}
