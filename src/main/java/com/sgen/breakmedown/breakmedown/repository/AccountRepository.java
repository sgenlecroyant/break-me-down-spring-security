package com.sgen.breakmedown.breakmedown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgen.breakmedown.breakmedown.model.account.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

}
