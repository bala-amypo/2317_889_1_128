package com.example.demo.service.impl;

import com.example.demo.entity.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest getGuestById(Long id) {
        return guestRepository.findById(id).orElseThrow();
    }

    @Override
    public void deactivateGuest(Long id) {
        Guest guest = getGuestById(id);
        guest.setActive(false);
        guestRepository.save(guest);
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }
}
