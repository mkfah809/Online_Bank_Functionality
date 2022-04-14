package com.coderscampus.assignment13.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.repository.UserRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	UserRepository userRepo;

	public Account saveAccount(Long size, Long userId) {
		User user = userRepo.findById(userId).orElse(null);
		String accountName = "Account# " + size;
		Account account = new Account();
		if (account.getAccountId() != null) {

		} else {
			List<User> users = new ArrayList<>();
			users.add(user);
			account.setAccountName(accountName);
			account.setUsers(users);
			account.getUsers().add(user);
			user.getAccounts().add(account);
		}

		return accountRepo.save(account);
	}

	public Account findById(Long accountId) {
		Optional<Account> accountOpt = accountRepo.findById(accountId);
		return accountOpt.orElse(new Account());
	}

	public void delete(Long userId) {
		accountRepo.deleteById(userId);
	}

}
