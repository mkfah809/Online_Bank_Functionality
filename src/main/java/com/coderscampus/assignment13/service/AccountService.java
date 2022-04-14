package com.coderscampus.assignment13.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	public Account saveAccount(Account account) {
		if (account.getAccountId() != null) {
		}
		return accountRepo.save(account);
	}

	public Account findById(Long accountId) {
		Optional<Account> accountOpt = accountRepo.findById(accountId);
		return accountOpt.orElse(new Account());
	}

	public void delete(Long accountId) {
		accountRepo.deleteById(accountId);
		System.out.println(accountId + "HAS BEEN DELETED");

	}

	public Account createAccount(Long userId, Long size) {
		Account account = new Account();
		setAccountForExistingUser(userId, size, account, new ArrayList<>());
		return accountRepo.save(account);
	}

	private void setAccountForExistingUser(Long userId, Long size, Account account, List<User> users) {
		users.add(userRepo.findById(userId).orElse(null));
		account.setAccountName("Account#" + size);
		account.setUsers(users);
		account.getUsers().add(userRepo.findById(userId).orElse(null));
		userRepo.findById(userId).orElse(null).getAccounts().add(account);
	}

}
