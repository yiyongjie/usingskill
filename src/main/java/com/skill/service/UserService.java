package com.skill.service;

import com.skill.common.page.PageInfo;
import com.skill.model.User;
import com.skill.model.dto.UserDTO;
import com.skill.model.vo.UserVO;

import java.util.List;

public interface UserService {
    List<UserVO> listUser(UserDTO dto);

    PageInfo<UserVO> pageUser(UserDTO dto);
}
