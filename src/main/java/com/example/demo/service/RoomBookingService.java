package com.example.demo.service;

import com.example.demo.entity.RoomBooking;

import java.util.List;

public interface RoomBookingService {

    List<RoomBooking> getBookingsForGuest(Long guestId);
}
