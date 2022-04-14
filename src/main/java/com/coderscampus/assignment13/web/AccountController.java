package com.coderscampus.assignment13.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	UserService userService;

	

	//Create new account button
	@GetMapping("/users/{userId}/accounts")
	public String getCreateAccount(ModelMap model, Long accountId, @PathVariable Long userId) {
		model.put("user", userService.findById(userId));
		model.put("account", accountService.findById(accountId));
		return "account";
	}
	
	//save button
	@PostMapping("/users/{userId}/accounts")
	public String postCreateAccount(@PathVariable Long userId) {
		System.out.println("user id is" + userId);
		User user = userService.findById(userId);
		List<Account> accounts  = user.getAccounts();
		Long size = (long) (accounts.size()+1);
		Account account = accountService.saveAccount(size, userId);
		return "redirect:/users/{userId}/accounts/" + account.getAccountId();

	}
	
	@GetMapping("/users/{userId}/accounts/{accountId}")
	public String getOneAccount(ModelMap model, @PathVariable Long accountId, @PathVariable Long userId, User user) {
		model.put("user", userService.findById(userId));
		model.put("account", accountService.findById(accountId));
		return "account";
		
	}
	
//   accountName links
	@PostMapping("/users/{userId}/accounts/{accountId}")
	public String postOneAccount(Long userId, Long size) {
		accountService.saveAccount(userId,size);
		return "redirect:/users/" + userId;
	}

}
