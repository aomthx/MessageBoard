package com.messageboard.service;

import com.messageboard.entity.User;
import com.messageboard.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int register(String username, String email, String password) {
        return userMapper.insert(username, email, password);
    }

    public User login(String email, String password) {
        User user = userMapper.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    public int setPassword(String email, String password) {
        return userMapper.updatePasswordByEmail(email, password);
    }

    public boolean setStatus(Integer id, Integer currentStatus) {
        Integer nextStatus = (currentStatus != null && currentStatus == 0) ? 1 : 0;
        return userMapper.updateStatus(id, nextStatus) > 0;
    }
}
