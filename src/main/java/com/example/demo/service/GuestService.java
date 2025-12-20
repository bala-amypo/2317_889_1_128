package com.example.demo.service;

import com.example.demo.entity.Guest;

import java.util.List;

public interface GuestService {

    Guest getGuestById(Long id);

    void deactivateGuest(Long id);

    List<Guest> getAllGuests();
}
