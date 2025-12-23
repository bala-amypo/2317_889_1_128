package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.RoomBookingService;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;

    public RoomBookingServiceImpl(RoomBookingRepository roomBookingRepository) {
        this.roomBookingRepository = roomBookingRepository;
    }

    @Override
    public RoomBooking createBooking(RoomBooking booking) {
        LocalDate in = booking.getCheckInDate();
        LocalDate out = booking.getCheckOutDate();

        if (in.isAfter(out)) {
            throw new IllegalArgumentException("Check-in date must be before check-out");
        }
        return roomBookingRepository.save(booking);
    }

    @Override
    public RoomBooking updateBooking(Long id, RoomBooking update) {
        RoomBooking existing = roomBookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found with id " + id));

        existing.setCheckInDate(update.getCheckInDate());
        existing.setCheckOutDate(update.getCheckOutDate());

        return roomBookingRepository.save(existing);
    }

    @Override
    public List<RoomBooking> getBookingsForGuest(Long guestId) {
        return roomBookingRepository.findByGuestId(guestId);
    }
}
