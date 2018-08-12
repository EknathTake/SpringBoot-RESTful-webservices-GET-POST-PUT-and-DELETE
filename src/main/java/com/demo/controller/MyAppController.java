package com.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.User;

@RestController
public class MyAppController {
	
	List<User> users;
	
	/**
	 * init all the records
	 */
	@PostConstruct
	public void initUsers() {
		users = new ArrayList<>();
		users.add(new User(1, "Eknath Take", 26, "Yenora, Jalna"));
		users.add(new User(2, "Rameshwar", 25, "Jalna"));
		users.add(new User(3, "Namdev", 27, "Parbhani"));
		users.add(new User(4, "Foo", 26, "Pune"));
		
	}
	
	/**
	 * get all record
	 * @param id
	 * @return User
	 */
	@GetMapping(path="/users")
	public List<User> getAllUsers() {
		return users;
	}
	
	/**
	 * get single record
	 * @param id
	 * @return User
	 */
	@GetMapping(path="/users/{user-id}")
	public User getOneUsersById(@PathVariable("user-id")long id) {
		User emptyUser = new User();
		emptyUser.setStatus("No record found");
		return users.stream()
					.filter(user-> user.getId()==id)
					.findAny()
					.orElse(emptyUser);
	}
	
	/**
	 * Saves the user record
	 * @param user
	 * @return status
	 */
	@PostMapping(path="/users")
	public String saveUser(@RequestBody User user) {
		
		boolean isAdded = users.add(user);
		String status = (isAdded) ? "User Added Successfully" :" User not added successfully";
		
		return status;
	}
	
	/**
	 * Updates the user record
	 * @param user
	 * @return status
	 */
	@PutMapping(path="/users")
	public String updateUserById(@RequestBody User user) {
		int[] index = {-1};
		users.stream()
			.peek(u -> index[0]++) //increment every element encounter
			.filter(u -> user.getId() == u.getId())
			.findFirst()
			.get();
		
		String status ="Record not updated";
		if(index[0] !=0) {
			users.set(index[0], user);
			status = "Record updated";
		}
		return status;
	}

	/**
	 * Removes the user record from the list
	 * @param id
	 * @return status
	 */
	@DeleteMapping(path="/users/{user-id}")
	public String removeRecord(@PathVariable(name="user-id") long id) {
		
		boolean isRemoved = users.removeIf(user -> user.getId() == id);
		String status = (isRemoved) ? "User Removed Successfully" :" User not found";
		return status;
	}

}
