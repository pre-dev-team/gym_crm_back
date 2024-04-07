package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface
AuthMapper {

    public int saveAccount(@Param("roleId")int roleId, @Param("account")Account account);
    public int saveUser(@Param("accountId") int accountId);
    public int saveTrainer(@Param("accountId")int accountId);

    public Account findAccountByUsername(String username);
    public Account findAccountByPhone(String phone);
    public Account findAccountByAccountId(String accountId);
    public Account findAccountByUserId(int userId);
    public Account findAccountByTrainerId(int trainerId);


//    public List<User> findUsers(
//            @Param("startIndex") int startIndex,
//            @Param("count") int count,
//            @Param("userUserId") int userUserId);



}
