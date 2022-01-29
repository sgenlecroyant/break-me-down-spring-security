package com.sgen.breakmedown.breakmedown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgen.breakmedown.breakmedown.model.Subscription;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Integer>{

}
