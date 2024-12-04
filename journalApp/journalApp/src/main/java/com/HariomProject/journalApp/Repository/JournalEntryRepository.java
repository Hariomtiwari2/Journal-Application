package com.HariomProject.journalApp.Repository;
import com.HariomProject.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalEntryRepository extends MongoRepository<JournalEntry , ObjectId> {

	List<JournalEntry> findByTitle(String title);
//	List<JournalEntry> findByUserName(String userName);
}
