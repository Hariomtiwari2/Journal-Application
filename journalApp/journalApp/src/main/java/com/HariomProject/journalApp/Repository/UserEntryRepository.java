package com.HariomProject.journalApp.Repository;
import com.HariomProject.journalApp.entity.JournalEntry;
import com.HariomProject.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface UserEntryRepository extends MongoRepository<User, ObjectId> {

	User findByUserName(String username);
	void deleteByUserName(String name);
}
