package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserProfile;

@RestController
public class UserProfileController {

	private Map<String, UserProfile> userMap;
	
	@PostConstruct
	public void init() {
		userMap = new HashMap<>();
		userMap.put("1", new UserProfile("1", "dsg", "111-222-333", "서울시 기흥구"));
		userMap.put("2", new UserProfile("2", "dsg", "111-222-333", "서울시 기흥구"));
		userMap.put("3", new UserProfile("3", "dsg", "111-222-333", "서울시 기흥구"));
	}
	
	// detail
	@GetMapping("/user/{id}")
	public UserProfile getUserProfile(@PathVariable("id") String id) {
		return userMap.get(id);
	}
	// select
	@GetMapping("/user/all")
	public List<UserProfile> getUserProfileList() {
		return new ArrayList<>(userMap.values());
	}
	
	// insert
	@PutMapping("/user/{id}")
	public void putUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
		UserProfile userProfile = new UserProfile(id, name, phone, address);
		
		userMap.put(id, userProfile);
	}
	
	// update
	@PostMapping("/user/{id}")
	public void postUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
		UserProfile userProfile = userMap.get(id);
		userProfile.setName(name);
		userProfile.setPhone(phone);
		userProfile.setAddress(address);
	}
	
	// delete
	@DeleteMapping("/user/{id}")
	public void deleteUserProfile(@PathVariable("id") String id) {

		userMap.remove(id);
		
	}
	
}
