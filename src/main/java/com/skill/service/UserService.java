package com.skill.service;

import com.github.pagehelper.PageInfo;
import com.skill.model.User;

import java.util.List;

public interface UserService {
    List<User> listUser(User user);

    PageInfo<User> pageUser(User user);
}
