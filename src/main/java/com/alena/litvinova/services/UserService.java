package com.alena.litvinova.services;

import com.alena.litvinova.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}