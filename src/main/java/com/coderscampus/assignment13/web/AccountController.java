package com.coderscampus.assignment13.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.Address;
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
		System.out.println("account id is"+accountId);
		model.put("account", new Account());
		System.out.println("account id is"+accountId);

		return "account";
	}
	
	//save button
	@PostMapping("/users/{userId}/accounts")
	public String postCreateAccount(Account account, User user) {
		System.out.println("account name is " + account.getAccountName());
		accountService.saveAccount(account, user);
		return "redirect:/users/{userId}/accounts/" + account.getAccountId();

	}
	
	@GetMapping("/users/{userId}/accounts/{accountId}")
	public String getOneAccount(ModelMap model, @PathVariable Long accountId, @PathVariable Long userId,
			Account account) {
		model.put("account", accountService.findById(accountId));
		model.put("user", userService.findById(userId));
		return "account";
	}
	
//   accountName links
	@PostMapping("/users/{userId}/accounts/{accountId}")
	public String postOneAccount(User user, Account account) {
		accountService.saveAccount(account,user);
		return "redirect:/users/" + user.getUserId();
	}

}
