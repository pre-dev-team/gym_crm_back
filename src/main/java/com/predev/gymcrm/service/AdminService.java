package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.resp.AdminSearchTrainerRespDto;
import com.predev.gymcrm.dto.resp.AdminSearchUserRespDto;
import com.predev.gymcrm.entity.User;
import com.predev.gymcrm.repository.AdminMapper;
import com.predev.gymcrm.repository.ReservationMapper;
import com.predev.gymcrm.repository.ReviewMapper;
import com.predev.gymcrm.repository.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private AdminMapper adminMapper;

    public List<AdminSearchUserRespDto> SearchUsersByName(String name) {
        List<User> users = authMapper.findUsersByName(name);
        if(users.size() == 0) {
            return null;
        }
        return users.stream().map(user ->
             AdminSearchUserRespDto.builder()
                    .userId(user.getUserId())
                    .name(user.getAccount().getName())
                    .ReservationCount(
                            reservationMapper.findReservationCountByUserId(user.getUserId())
                    )
                    .build()
        ).collect(Collectors.toList());
    }

    public List<AdminSearchTrainerRespDto> SearchTrainers() {

    }

}
