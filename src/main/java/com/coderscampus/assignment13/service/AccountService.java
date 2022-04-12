package com.coderscampus.assignment13.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepo;

	public Account saveAccount(Account account, User user) {

		if (account.getAccountId() != null) {

		} else {
//			System.out.println("account id is " + account.getAccountId());
//			account = new Account();
//			System.out.println("account id after initiaze a new account is " + account.getAccountId());
//			System.out.println();
			user.getAccounts().add(account);
//			account.getUsers().add(user);

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
