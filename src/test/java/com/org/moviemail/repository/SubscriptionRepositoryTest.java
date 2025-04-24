package com.org.moviemail.repository;

import com.org.moviemail.entity.Customer;
import com.org.moviemail.entity.Subscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // ✅ Use real MySQL
@EntityScan(basePackages = "com.org.moviemail.entity")
class SubscriptionRepositoryTest {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Customer customer;
    private Subscription subscription;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .name("Alice")
                .email("alice@sub.com")
                .build();
        testEntityManager.persist(customer);

        subscription = Subscription.builder()
                .name("Gold")
                .dvdsAtHome(3)
                .dvdsPerMonth(5)
                .pricePerMonth(13.00)
                .active(true)
                .customer(customer)
                .build();
    }

    @Test
    @DisplayName("Save and retrieve subscription")
    void whenSave_thenRetrieveByCustomerId() {
        Subscription saved = subscriptionRepository.saveAndFlush(subscription);

        List<Subscription> found = subscriptionRepository.findByCustomerId(customer.getId());

        assertFalse(found.isEmpty());
        assertEquals("Gold", found.get(0).getName());
        assertEquals(13.00, found.get(0).getPricePerMonth());
    }

    @Test
    @DisplayName("Duplicate subscription (same name + customer) should fail if constraint added")
    void whenDuplicateSubscription_thenThrowException() {
        subscriptionRepository.saveAndFlush(subscription);

        Subscription duplicate = Subscription.builder()
                .name("Gold")
                .dvdsAtHome(2)
                .dvdsPerMonth(3)
                .pricePerMonth(11.00)
                .active(true)
                .customer(customer)
                .build();

        // only valid if you've enforced uniqueness at entity level
        assertThrows(DataIntegrityViolationException.class,
                () -> subscriptionRepository.saveAndFlush(duplicate));
    }

    @Test
    @DisplayName("Delete subscription")
    void whenDeleteById_thenRemove() {
        Subscription saved = subscriptionRepository.saveAndFlush(subscription);

        subscriptionRepository.deleteById(saved.getId());
        testEntityManager.flush();

        List<Subscription> remaining = subscriptionRepository.findByCustomerId(customer.getId());
        assertTrue(remaining.isEmpty());
    }
}

