package com.skill.dao;

import org.apache.ibatis.annotations.Mapper;
import com.skill.model.User;
import com.skill.model.vo.UserVO;
import java.util.List;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface UserMapper {
    int addUser(User user);
    int updateUser(User user);
    @Delete("delete from user where id=#{id}")
    int deleteUser(int id);
    List<UserVO> listUser(User user);
    int countUser(User user);
}