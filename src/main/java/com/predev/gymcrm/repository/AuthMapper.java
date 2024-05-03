package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface
AuthMapper {
    int saveAccount(@Param("roleId")int roleId, @Param("account")Account account);
    int saveUser(@Param("accountId") int accountId);
    int saveTrainer(@Param("accountId")int accountId);
    Account findAccountByNameAndEmail(@Param("name") String name, @Param("email") String email);
    Account findAccountByUserNameAndEmail(@Param("username") String username, @Param("email") String email);
    Account findAccountByUsername(String username);
    Account findAccountByPhone(String phone);
    Account findAccountByAccountId(int accountId);
    Account findAccountByTrainerId(int trainerId);
    Account findAccountByUserId(int userId);
    Integer findUserIdByAccountId(int accountId);
    Integer findTrainerIdByAccountId(int accountId);
    List<AdminSearchUser> findUserInfosWithReservationCountByName(String name);
    Account findAccountByOAuth2Name(String oAuth2Name);
    int deleteTrainer(int trainerId);
    void updateAccountPassword(Account account);
    int updateAdminPassword(String newPassword);
    int saveOAuth2(OAuth2 oAuth2);
    void updateAccountTemporaryPw(@Param("accountId") int accountId, @Param("password") String password);
}
