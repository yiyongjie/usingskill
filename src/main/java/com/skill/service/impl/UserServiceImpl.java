package com.skill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skill.common.model.base.OrderModel;
import com.skill.dao.UserMapper;
import com.skill.model.User;
import com.skill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUser(User user) {
        return userMapper.listUser(user);
    }
    @Override
    public PageInfo<User> pageUser(User user) {
        PageHelper.startPage(1,2);
        List<OrderModel> models=new ArrayList<>();
        models.add(OrderModel.desc("id"));
        user.setOrderBy(models);
        List<User> list=userMapper.listUser(user);
        PageInfo<User> page = new PageInfo<User>(list);
        return page;
    }
}
