package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.TrainerAddInbodyReqDto;
import com.predev.gymcrm.dto.resp.UserSearchInbodyRespDto;
import com.predev.gymcrm.dto.resp.TrainerSearchInbodyRespDto;
import com.predev.gymcrm.entity.Inbody;
import com.predev.gymcrm.repository.InbodyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InbodyService {

    @Autowired
    private InbodyMapper inbodyMapper;

    public int insertInbody(InbodyReqDto inbodyReqDto) {
        int successCount = 0;
        successCount =inbodyMapper.saveInbody(trainerAddInbodyReqDto.toInbodyEntity());
        return successCount;
    }

    public List<UserSearchInbodyRespDto> searchInbodyByUserId(int accountId) {
        List<Inbody> inbodyList = inbodyMapper.findInbodyByAccountId(accountId);
        return inbodyList.stream().map(Inbody::toInbodyRespDto).collect(Collectors.toList());
    }

    public List<TrainerSearchInbodyRespDto> searchInbodyInformation(int userId) {
        List<Inbody> inbodys = inbodyMapper.findInbodyByUserId(userId);
        return inbodys.stream().map(Inbody::toSearchInbodyRespDto).collect(Collectors.toList());
    }
}