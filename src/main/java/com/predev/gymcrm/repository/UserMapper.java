package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public int saveUser(User user);
//    public String findUserByUsername(String userUsername);
}
