package com.coderscampus.assignment13.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public List<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public List<User> findByNameAndUsername(String name, String username) {
		return userRepo.findByNameAndUsername(name, username);
	}

	public List<User> findByCreatedDateBetween(LocalDate date1, LocalDate date2) {
		return userRepo.findByCreatedDateBetween(date1, date2);
	}

	public User findExactlyOneUserByUsername(String username) {
		List<User> users = userRepo.findExactlyOneUserByUsername(username);
		if (users.size() > 0)
			return users.get(0);
		else
			return new User();
	}

	public Set<User> findAll() {
		return userRepo.findAllUsersWithAccountsAndAddresses();
	}

	public User findById(Long userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		return userOpt.orElse(new User());
	}

	public User saveUser(User user) {
		Account account = new Account();
		Address address = new Address();
		
		if (user.getUserId() == null) {
			System.out.println("User doesn't exist");
		} else {
			System.out.println("User exists");
		}

		if (address.getUserId() == null) {
			System.out.println("address doesn't exist");
			setAddressInformation(user, address);
		} else {
			System.out.println("user has address");
		}
		
		if(user.getAccounts() != null) {
			System.out.println("user has accounts");
		} else {
			System.out.println("user doesn't have any accounts yet");
		}
		return userRepo.save(user);
	}

	private void setAddressInformation(User user, Address address) {
		address.setAddressLine1(user.getAddress().getAddressLine1());
		address.setAddressLine2(user.getAddress().getAddressLine2());
		address.setCity(user.getAddress().getCity());
		address.setRegion(user.getAddress().getRegion());
		address.setCountry(user.getAddress().getCountry());
		address.setZipCode(user.getAddress().getZipCode());
		address.setUserId(user.getUserId());
		address.setUser(user);
		user.setAddress(address);
	}

	public void delete(Long userId) {
		userRepo.deleteById(userId);
	}
}
