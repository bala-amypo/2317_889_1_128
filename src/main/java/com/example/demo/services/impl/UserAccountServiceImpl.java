package com.example.demo.services.impl;

import java.util.Optional;
import java.util.*;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.services.UserAccountService;

public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    Map<Long, UserAccount> records = new HashMap<>();
    
    @Override
    public UserAccount getUserDataFromDB(Long id) {
        return records.get(id);
        // return userAccountRepository.findById(id);
    }

    @Override
    public UserAccount postUserDateToDB(UserAccount userAccount) {
        records.put(userAccount.getId(), userAccount);
        return userAccount;
        // return userAccountRepository.save(userAccount);
    }
    
}
