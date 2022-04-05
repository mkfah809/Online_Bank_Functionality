package com.coderscampus.assignment13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.assignment13.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
