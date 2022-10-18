package com.rusile.web_lab2.model;

import com.rusile.web_lab2.entity.User;

import java.time.Instant;
import java.util.Optional;

public interface UserDAO {

    Optional<User> getUserByToken(String token);

    void saveUser(User user);

    void updateUser(User user);
}
