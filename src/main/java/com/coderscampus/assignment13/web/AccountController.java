package com.coderscampus.assignment13.web;

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

	@GetMapping("/users/{userId}/accounts/{accountId}")
	public String getAccount(ModelMap model, @PathVariable Long accountId, @PathVariable Long userId) {
		model.put("account", accountService.findById(accountId));
		model.put("user", userService.findById(userId));
		return "account";
	}

	@PostMapping("/users/{userId}/accounts/{accountId}")
	public String postAccount(Account account, User user) {
		accountService.saveAccount(account);
		return "redirect:/users/" + user.getUserId();
	}

	@PostMapping("/users/{userId}/account/{accountId}/delete")
	public String deleteOneAccount(@PathVariable Long accountId, User user) {
		System.out.println("userId is: " + user.getUserId());
		accountService.delete(accountId);
		return "redirect:/users";
	}

	@PostMapping("/users/{userId}/accounts")
	public String postOneAccount(@PathVariable Long userId) {
		return "redirect:/users"+userId;
	}
}
