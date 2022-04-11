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

	@GetMapping("/users/{userId}/accounts/{accountId}")
	public String getOneAccount(ModelMap model, @PathVariable Long accountId, @PathVariable Long userId,
			Account account) {
		model.put("account", accountService.findById(accountId));
		model.put("user", userService.findById(userId));
		return "account";
	}

	@PostMapping("/users/{userId}/accounts/{accountId}")
	public String postEditAccount(User user, Account account) {
		accountService.saveAccount(account);
		return "redirect:/users/" + user.getUserId();
	}

//	@GetMapping("/users/{userId}/accounts")
//	public String getCreateAccount(ModelMap model, @PathVariable Long userId, Long accountId) {
//		model.put("account", accountService.findById(accountId));
//		model.put("user", userService.findById(userId));
//		return "createAccount";
//	}
	
	@PostMapping("/users/{userId}/accounts")
	public String postCreateAccount(Account account) {
		System.out.println(account.getAccountName());
		accountService.saveAccount(account);
		return "createAccount";
		
	}


}
