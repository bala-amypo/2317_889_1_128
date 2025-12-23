package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RoomBooking {

    @Id
    @GeneratedValue
    private Long id;

    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Boolean active = true;

    @ManyToOne
    private Guest guest;

    @ManyToMany
    private Set<Guest> roommates = new HashSet<>();

    public RoomBooking(Long id, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate, Boolean active,
            Guest guest, Set<Guest> roommates) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.active = active;
        this.guest = guest;
        this.roommates = roommates;
    }

    public RoomBooking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Set<Guest> getRoommates() {
        return roommates;
    }

    public void setRoommates(Set<Guest> roommates) {
        this.roommates = roommates;
    }

    
}
