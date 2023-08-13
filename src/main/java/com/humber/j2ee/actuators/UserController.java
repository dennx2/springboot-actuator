package com.humber.j2ee.actuators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserDatabase database;

	@GetMapping("/loadUsers")
	public List<User> getUsers(){
		return database.getAllUsers();
	}
}
