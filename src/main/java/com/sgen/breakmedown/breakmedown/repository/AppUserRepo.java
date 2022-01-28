package com.sgen.breakmedown.breakmedown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgen.breakmedown.breakmedown.model.AppUser;

@Repository(value = "AppUserRepo")
public interface AppUserRepo extends JpaRepository<AppUser, Integer>{

}
