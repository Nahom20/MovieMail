package com.org.moviemail.repository;

import com.org.moviemail.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("SELECT SUM(s.pricePerMonth) FROM Subscription s WHERE s.active = true")
    Double calculateTotalRevenue();

}
