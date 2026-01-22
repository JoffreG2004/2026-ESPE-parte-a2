package es.upm.grise.profundizacion.subscriptionService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubscriptionServiceTest {
	
	private SubscriptionService service;
	private User user;
	
	@BeforeEach
	public void setUp() {
		service = new SubscriptionService();
		user = new User();
	}
	
	@Test
	public void smokeTest() {}
	
	@Test
	public void addSubscriber_withValidUser_shouldAddToSubscribers() throws Exception {
		user.setEmail("test@example.com");
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		
		service.addSubscriber(user);
		
		assertTrue(service.getSubscribers().contains(user));
		assertEquals(1, service.getSubscribers().size());
	}
	
	@Test
	public void addSubscriber_withNullUser_shouldThrowNullUserException() {
		assertThrows(NullUserException.class, () -> {
			service.addSubscriber(null);
		});
	}
	
	@Test
	public void addSubscriber_withExistingUser_shouldThrowExistingUserException() throws Exception {
		user.setEmail("test@example.com");
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		
		service.addSubscriber(user);
		
		assertThrows(ExistingUserException.class, () -> {
			service.addSubscriber(user);
		});
	}
	
	@Test
	public void addSubscriber_withLocalDeliveryAndNonNullEmail_shouldThrowLocalUserDoesNotHaveNullEMailException() {
		user.setEmail("test@example.com");
		user.setDelivery(Delivery.LOCAL);
		
		assertThrows(LocalUserDoesNotHaveNullEMailException.class, () -> {
			service.addSubscriber(user);
		});
	}
	
	@Test
	public void addSubscriber_withLocalDeliveryAndNullEmail_shouldAddToSubscribers() throws Exception {
		user.setEmail(null);
		user.setDelivery(Delivery.LOCAL);
		
		service.addSubscriber(user);
		
		assertTrue(service.getSubscribers().contains(user));
		assertEquals(1, service.getSubscribers().size());
	}
	
	@Test
	public void addSubscriber_withDoNotDeliverAndEmail_shouldAddToSubscribers() throws Exception {
		user.setEmail("test@example.com");
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		
		service.addSubscriber(user);
		
		assertTrue(service.getSubscribers().contains(user));
		assertEquals(1, service.getSubscribers().size());
	}
	
}
