package com.HariomProject.journalApp.Services;

import com.HariomProject.journalApp.Repository.UserEntryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServicesTest {

	@Autowired
	private UserEntryRepository userEntryRepository;
	@Test
	public void testAdd(){
		assertEquals(4,2+2);
		assertNotNull(userEntryRepository.findByUserName("hariom"));
	}

	public void test2(){

		assertNotNull(userEntryRepository.findByUserName("hariom"));
	}


}
