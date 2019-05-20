package com.skill.service;

import com.skill.model.User;

import java.util.List;

public interface UserService {
    List<User> listUser(User user);

    List<User> pageUser(User user);
}
