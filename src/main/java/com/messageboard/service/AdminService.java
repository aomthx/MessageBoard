package com.messageboard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Value("${messageboard.admin.username:root}")
    private String username;

    @Value("${messageboard.admin.password:12306}")
    private String password;

    public boolean login(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }
}
