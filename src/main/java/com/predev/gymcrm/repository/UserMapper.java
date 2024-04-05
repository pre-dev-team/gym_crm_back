package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface
UserMapper {
    public int saveUser(Account account);
    public Account findUserByUsername(String userUsername);
    public Account findUserByPhone(String userPhone);

    public RoleUserRegister findRoleRegisterByUserIdAndRoleId(@Param("userId") int userId, @Param("roleId") int roleId);

//    public List<User> findUsers(
//            @Param("startIndex") int startIndex,
//            @Param("count") int count,
//            @Param("userUserId") int userUserId);

    public int saveRole(@Param("userId") int userId, @Param("roleId") int roleId);


}
