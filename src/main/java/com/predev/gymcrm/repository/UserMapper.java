package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.RoleRegister;
import com.predev.gymcrm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface
UserMapper {
    public int saveUser(User user);
    public User findUserByUsername(String userUsername);

    public RoleRegister findRoleRegisterByUserIdAndRoleId(@Param("userId") int userId, @Param("roleId") int roleId);

    public List<User> findUsers(
            @Param("startIndex") int startIndex,
            @Param("count") int count,
            @Param("userUserId") int userUserId);
}
