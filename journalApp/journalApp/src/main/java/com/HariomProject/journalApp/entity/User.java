package com.HariomProject.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Document(collection = "user") // this is for mongodb collection
@Data
public class User {
	@Id
	private ObjectId id;
	@Indexed(unique = true)//  this will make username field unique
	@NonNull               //  this annotation will make sure that username field cannot be null
	private String userName;
	@NonNull                //  this annotation will make sure that password field cannot be null
	private String password;
	private List<String> roles; // this will tell what roles will user have like , creating entries, deleting them, logIn, LogOut, change password
	/*we want whenever a user is registerd his list of journal is created
	user --> journals
	 */
	@DBRef/*It establishes relation between two documents, this is similar to foreign key in relational db.*/
	private List<JournalEntry> journalEntries = new ArrayList<>();




	//  if any entity which is marked as @NonNull is made null
	//  then null pointer exception will be thrown !
	//  this will  not be saved in database
}
