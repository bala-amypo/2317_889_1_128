package com.example.demo.repository;

public interface DigitalKeyRepository {
}package com.example.demo.repository;

import com.example.demo.entity.DigitalKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {

    Optional<DigitalKey> findByBookingIdAndActiveTrue(Long bookingId);
}
