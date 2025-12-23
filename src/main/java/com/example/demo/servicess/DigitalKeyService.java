package com.example.demo.service;

import java.util.List;

import com.example.demo.model.DigitalKey;

public interface DigitalKeyService {

    DigitalKey generateKey(Long bookingId);

    DigitalKey getActiveKeyForBooking(Long bookingId);

    List<DigitalKey> getKeysForGuest(Long guestId);
}
