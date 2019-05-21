package com.skill.service;

import com.github.pagehelper.PageInfo;
import com.skill.model.User;
import com.skill.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<User> listUser(UserDTO dto);

    PageInfo<User> pageUser(UserDTO dto);
}
