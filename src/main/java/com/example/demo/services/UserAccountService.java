package com.example.demo.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.entity.UserAccount;

@Service
public interface UserAccountService {
    UserAccount getUserDataFromDB(Long id);
    UserAccount postUserDateToDB(UserAccount userAccount);
}
