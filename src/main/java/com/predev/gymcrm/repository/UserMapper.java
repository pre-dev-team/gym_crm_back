package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.RoleUserRegister;
import com.predev.gymcrm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface
UserMapper {
    public int saveUser(User user);
    public User findUserByUsername(String userUsername);
    public User findUserByPhone(String userPhone);

    public RoleUserRegister findRoleRegisterByUserIdAndRoleId(@Param("userId") int userId, @Param("roleId") int roleId);

//    public List<User> findUsers(
//            @Param("startIndex") int startIndex,
//            @Param("count") int count,
//            @Param("userUserId") int userUserId);

    public int saveRole(@Param("userId") int userId, @Param("roleId") int roleId);


}
