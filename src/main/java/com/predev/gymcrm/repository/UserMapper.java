package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.RoleRegister;
import com.predev.gymcrm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public int saveUser(User user);
    public User findUserByUsername(String userUsername);
    public RoleRegister findRoleRegisterByUserIdAndRoleId(@Param("userId") int userId, @Param("roleId") int roleId);
}
