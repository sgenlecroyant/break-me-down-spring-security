package com.sgen.breakmedown.breakmedown.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.sgen.breakmedown.breakmedown.model.Subscription;
import com.sgen.breakmedown.breakmedown.repository.SubscriptionRepo;
import com.sgen.breakmedown.breakmedown.subscriptionType.SubscriptionType;

@SpringBootTest
class AppUserSubscriptionServiceTest {
	
	@Mock
	private SubscriptionRepo subscriptionRepo;
	
	@InjectMocks
	private SubscriptionService subscriptionService;

	@Test
	public void testFetchAllSubscriptionsSuccess() {
		List<Subscription> subscriptions = List.of(
				new Subscription(SubscriptionType.S4G, "4G Net", 1200f),
				new Subscription(SubscriptionType.S4G, "3G Net", 1000f));
		Mockito.when(this.subscriptionRepo.findAll()).thenReturn(subscriptions);
		List<Subscription> allSubscriptions = this.subscriptionService.fetchAllSubscriptions();
		
		Mockito.verify(this.subscriptionRepo).findAll();
		
		assertThat(allSubscriptions.size()).isEqualTo(subscriptions.size());
		
	}
	

}
