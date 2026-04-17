package com.obando.crud_users.service;

import com.obando.crud_users.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByEmail(String email);
}
