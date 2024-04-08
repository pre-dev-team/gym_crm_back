package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.resp.SearchMyMembersRespDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.repository.AuthMapper;
import com.predev.gymcrm.repository.ReservationMapper;
import com.predev.gymcrm.repository.TrainerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    @Autowired
    private TrainerMapper trainerMapper;

    @Autowired
    private AuthMapper authMapper;

    public List<SearchMyMembersRespDto> selectMyMembers(int trainerAccountId) {
        List<Integer> userIds = trainerMapper.findReservedUserIdsByTrainerAccountId(trainerAccountId);
        List<Account> myMembers = userIds.stream().map(id -> authMapper.findAccountByUserId(id)).collect(Collectors.toList());
        return myMembers.stream().map(account -> SearchMyMembersRespDto.builder()
                .accountId(account.getAccountId())
                .name(account.getName())
                .phone(account.getPhone())
                .email(account.getEmail())
                .build()
        ).collect(Collectors.toList());
    }
}
