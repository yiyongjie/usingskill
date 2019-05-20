//package com.skill.service.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.skill.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public List<User> listUser(User user) {
//        return userMapper.listUser(user);
//    }
//    @Override
//    public List<User> pageUser(User user) {
//        PageHelper.startPage(1,2);
//        List<User> page=userMapper.listUser(user);
//        return page;
//    }
//}
