package com.sgen.breakmedown.breakmedown.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.Banner;
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
	
	@Test
	public void testFetchAllSubscriptionsFailure() {
		List<Subscription> subscriptions = List.of(
				new Subscription(SubscriptionType.S4G, "4G Net", 1200f),
				new Subscription(SubscriptionType.S4G, "3G Net", 1000f));
		List<Subscription> allSubscriptions = this.subscriptionService.fetchAllSubscriptions();
		
		assertThat(allSubscriptions.size()).isLessThan(subscriptions.size());
	}
	
	@Test
	public void testDeleteSubscriptionByIdFailure() {
		Mockito.when(this.subscriptionRepo.findById(anyInt())).thenReturn(Optional.empty());
		Mockito.verify(this.subscriptionRepo, never()).delete(any());
		
		assertThatThrownBy(() -> this.subscriptionService.deleteSubscriptionById(anyInt()))
							.hasMessage("subscription not available");
				
	}
}
