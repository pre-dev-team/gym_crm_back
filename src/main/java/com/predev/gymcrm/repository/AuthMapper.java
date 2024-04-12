package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface
AuthMapper {

    public int saveAccount(@Param("roleId")int roleId, @Param("account")Account account);
    public int saveUser(@Param("accountId") int accountId);
    public int saveTrainer(@Param("accountId")int accountId);

    public Account findAccountByUsername(String username);
    public Account findAccountByPhone(String phone);
    public Account findAccountByAccountId(int accountId);
    public Account findAccountByUserId(int userId);
    public Integer findUserIdByAccountId(int accountId);
    public Integer findTrainerIdByAccountId(int accountId);
    public Account findAccountByTrainerId(int trainerId);
    public List<User> findUsersByName(String name);

}
