package com.example.demo.services;

import java.util.Optional;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {
    UserAccount getUserDataFromDB(Long id);
    UserAccount postUserDateToDB(UserAccount userAccount);
}
