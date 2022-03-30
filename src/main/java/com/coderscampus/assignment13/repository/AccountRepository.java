package com.coderscampus.assignment13.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
//	@Query("select a from Account a"
//			+ "from users u, user_account ua, accounts"
//			+ "where ua.user_id = u.user_id"
//			+ "and   ua.account_id = a.account_id"
//			+ "and   u.user_id = :u.user_id")
//	List<User> findAllAccountsForExactlyOneUser(Long accountId);
	
}
