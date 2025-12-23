package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Guest {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String fullName;
    private String phoneNumber;
    private Boolean verified = false;
    private Boolean active = true;
    private String role = "ROLE_USER";

    // getters & setters

    // IMPORTANT for Many-to-Many uniqueness test
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;
        Guest guest = (Guest) o;
        return Objects.equals(id, guest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
