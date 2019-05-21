package com.skill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skill.common.model.base.OrderModel;
import com.skill.dao.UserMapper;
import com.skill.model.User;
import com.skill.model.dto.UserDTO;
import com.skill.service.UserService;
import com.skill.util.DeepFieldCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUser(UserDTO dto) {
        User user=DeepFieldCopy.transform(dto,User.class);
        return userMapper.listUser(user);
    }
    @Override
    public PageInfo<User> pageUser(UserDTO dto) {
        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        List<OrderModel> models=new ArrayList<>();
        models.add(OrderModel.desc("id"));
        dto.setOrderBy(models);
        User user=DeepFieldCopy.transform(dto,User.class);
        List<User> list=userMapper.listUser(user);
        PageInfo<User> page = new PageInfo<User>(list);
        return page;
    }
}
