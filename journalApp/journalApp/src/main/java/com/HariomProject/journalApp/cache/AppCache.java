package com.HariomProject.journalApp.cache;

import com.HariomProject.journalApp.Repository.ConfigJournalRepository;
import com.HariomProject.journalApp.entity.ConfigJournalApp;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

	public enum Keys{
		WEATHER_API;
	}

	@Autowired
	private ConfigJournalRepository configJournalRepository;

	public Map<String , String> AppCache ;

	@PostConstruct
	public void init(){
		AppCache = new HashMap<>();
		List<ConfigJournalApp> all = configJournalRepository.findAll();
		for (ConfigJournalApp configJournalApp :all){
			AppCache.put(configJournalApp.getKey(), configJournalApp.getValue());
		}
	}
}
