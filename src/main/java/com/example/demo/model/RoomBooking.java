package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class RoomBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long guestId;

    private boolean active = true;

    // getters & setters
}
