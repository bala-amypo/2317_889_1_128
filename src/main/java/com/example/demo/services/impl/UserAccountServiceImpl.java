package com.example.demo.services.impl;

import java.util.Optional;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.services.UserAccountService;

public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }
    
    @Override
    public Optional<UserAccount> getUserDataFromDB(Long id) {
        return userAccountRepository.findById(id);
    }

    @Override
    public UserAccount postUserDateToDB(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }
    
}
