package com.HariomProject.journalApp.Repository;
import com.HariomProject.journalApp.entity.ConfigJournalApp;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalRepository extends MongoRepository<ConfigJournalApp, ObjectId> {





}
