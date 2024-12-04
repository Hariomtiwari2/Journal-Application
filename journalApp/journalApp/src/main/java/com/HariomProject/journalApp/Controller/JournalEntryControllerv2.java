package com.HariomProject.journalApp.Controller;
import com.HariomProject.journalApp.Services.JournalEntryService;
import com.HariomProject.journalApp.Services.UserEntryService;
import com.HariomProject.journalApp.entity.JournalEntry;
import com.HariomProject.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

	@Autowired
	private JournalEntryService journalEntryService;
	@Autowired
	private UserEntryService userEntryService;

//  End point 1
	@GetMapping(/* no need to pass parameter here since we will get info after passing authentication */)
	public ResponseEntity<?> getAllJournalEntryOfUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userEntryService.findByUserName(userName);
		List<JournalEntry> list = user.getJournalEntries();
		if (list!=null && !list.isEmpty()){
 		return new ResponseEntity<>(list, HttpStatus.OK);
	    }
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	//  End point 2
	@PostMapping()
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userName = authentication.getName();
			journalEntryService.saveEntry(myEntry, userName);
			return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
		}catch (Exception exception){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	//  End point 3
	@GetMapping("id/{myId}")
	public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userEntryService.findByUserName(userName);
		List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
		if (!collect.isEmpty()){
			Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
			if (journalEntry.isPresent()){
				return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	//  End point 4
	@DeleteMapping("id/{myId}")
	public ResponseEntity<?> deleteEnteryById(@PathVariable ObjectId myId){
		Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		boolean removed = journalEntryService.deleteJournalByid(myId,username);
		if (removed){
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   }else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
	//  End point 5
//  1) find id which need to be updated

	@PutMapping("upd/{myId}")
	public ResponseEntity<?> updateEnteryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
		Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userEntryService.findByUserName(username);
		List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
		if (!collect.isEmpty()){
			Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
			if (journalEntry.isPresent()){
				JournalEntry old  = journalEntry.get();
				old.setTitle(newEntry.getTitle() != null && !newEntry.getContent().equals("") ? newEntry.getTitle(): old.getTitle());
				old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("")? newEntry.getContent(): old.getContent());
				journalEntryService.saveEntry(old);
				return new ResponseEntity<>(old, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

//	End point 6
	@GetMapping("/title/{title}")
	public ResponseEntity<List<JournalEntry>> getEntriesByTittle(@PathVariable String title){
		List<JournalEntry> entries = journalEntryService.getEntriesByTittle(title);
		if (entries != null && !entries.isEmpty()){
			return new ResponseEntity<>(entries , HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
