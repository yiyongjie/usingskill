package com.skill.service;

import com.github.pagehelper.Page;
import com.skill.dao.UserMapper;
import com.skill.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> listUser(User user);

    List<User> pageUser(User user);
}
