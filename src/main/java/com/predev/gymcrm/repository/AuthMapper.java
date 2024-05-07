package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface
AuthMapper {

    public int saveAccount(@Param("roleId")int roleId, @Param("account")Account account);
    public int saveUser(@Param("accountId") int accountId);
    public int saveTrainer(@Param("accountId")int accountId);
    public Account findAccountByNameAndEmail(@Param("name") String name, @Param("email") String email);
    public Account findAccountByUserNameAndEmail(@Param("username") String username, @Param("email") String email);
    public Account findAccountByUsername(String username);
    public Account findAccountByPhone(String phone);
    public Account findAccountByAccountId(int accountId);
    public Account findAccountByTrainerId(int trainerId);
    public Integer findUserIdByAccountId(int accountId);
    public Integer findTrainerIdByAccountId(int accountId);
    public List<AdminSearchUser> findUserInfosWithReservationCountByName(String name);
    public Account findAccountByOAuth2Name(String oAuth2Name);
    public int deleteTrainer(int trainerId);
    public void updateAccountPassword(Account account);
    public int updateAdminPassword(String newPassword);
    public int saveOAuth2(OAuth2 oAuth2);
    public void updateAccountTemporaryPw(@Param("accountId") int accountId, @Param("password") String password);
}
