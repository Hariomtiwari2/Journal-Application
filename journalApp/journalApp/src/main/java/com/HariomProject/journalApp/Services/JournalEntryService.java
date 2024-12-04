package com.HariomProject.journalApp.Services;

/*
*  How web app works what is called first
*  Controller --> Service --> Repository --> Entity (Object Class)
*  what to create first while creating project
* Entity (Object Class) --> Repository --> Service --> Controller
 */



import com.HariomProject.journalApp.Repository.JournalEntryRepository;
import com.HariomProject.journalApp.entity.JournalEntry;
import com.HariomProject.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService   {

	@Autowired
	private JournalEntryRepository journalEntryRepository;
	@Autowired
	private UserEntryService userEntryService;


	@Transactional
	public void saveEntry(JournalEntry journalEntry, String userName){
		try {
			User user = userEntryService.findByUserName(userName);
			journalEntry.setDate(LocalDateTime.now());
			JournalEntry saved = journalEntryRepository.save(journalEntry);
			user.getJournalEntries().add(saved);
			userEntryService.saveUser(user);
		}catch (Exception e){
			System.out.println(e);
			throw new RuntimeException("An error has occurred while saving the entry.",e);
		}

	}

	public void saveEntry(JournalEntry journalEntry){
			journalEntryRepository.save(journalEntry);
	}

	public List<JournalEntry> getAll(){
		return journalEntryRepository.findAll();
	}

	public Optional<JournalEntry> findById(ObjectId id){
		return journalEntryRepository.findById(id);
	}

	// method to delete mapping
	@Transactional
	public boolean deleteJournalByid(ObjectId id, String userName){
		boolean removed = false;
		try {
			User user = userEntryService.findByUserName(userName);
			 removed = user.getJournalEntries().removeIf(x ->x.getId().equals(id));
			if(removed) {
				userEntryService.saveUser(user);
				journalEntryRepository.deleteById(id);
			}
		}catch (Exception e){
			System.out.println(e);
			throw new RuntimeException("An error occurred while saving the entry,",e);
		}
		return removed;
	}

	public List<JournalEntry> getEntriesByTittle(String title){
		return journalEntryRepository.findByTitle(title);
	}

//	public List<JournalEntry> getEntriesByUserName(String user){
//		return journalEntryRepository.findByUserName(user);
//	}

}
