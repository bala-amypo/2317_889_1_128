package com.example.demo.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserAccount;
import com.example.demo.services.UserAccountService;


@RestController
public class UserAccountController {
    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/postUserAccountData")
    public UserAccount postUserData(UserAccount userAccount) {
        return userAccountService.postUserDateToDB(userAccount);
    }

    @GetMapping("/getUserAccountData")
    public Optional<UserAccount> getUserData(@PathVariable Long id) {
        return userAccountService.getUserDataFromDB(id);
    }

    @PutMapping("/updateData/{id}")
    public Optional<UserAccount> updateUserData(@PathVariable Long id, @RequestBody UserAccount userAccount) {
        return userAccountService.updateUserDataInDB(id, userAccount);
    }

    @DeleteMapping("/deleteUserAccountData/{id}")
    public String deleteUserData(@PathVariable Long id) {
        return userAccountService.deleteUserDataInDB(id);
    }
}
