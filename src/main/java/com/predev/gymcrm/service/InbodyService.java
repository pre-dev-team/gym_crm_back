package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.InbodyReqDto;
import com.predev.gymcrm.dto.resp.InbodyRespDto;
import com.predev.gymcrm.dto.resp.SearchInbodyRespDto;
import com.predev.gymcrm.dto.resp.SearchUnreservedTrainerRespDto;
import com.predev.gymcrm.entity.Inbody;
import com.predev.gymcrm.repository.InbodyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InbodyService {
    private final InbodyMapper inbodyMapper;

    @Autowired
    public InbodyService(InbodyMapper inbodyMapper) {
        this.inbodyMapper = inbodyMapper;
    }

    public int addInbody(InbodyReqDto inbodyReqDto) {
        int successCount = 0;
        successCount =inbodyMapper.insertInbody(inbodyReqDto.toInbodyEntity());

        return successCount;
    }

    public List<InbodyRespDto> getInbodyByAccountId(int accountId) {
        System.out.println("Received accountId in service: " + accountId);
        List<Inbody> inbodyList = inbodyMapper.findInbodyByAccountId(accountId);

        System.out.println("Retrieved inbodies from database: " + inbodyList);
        return inbodyList.stream()
                .map(Inbody::toInbodyRespDto) // 수정된 부분
                .collect(Collectors.toList());
    }

    public List<InbodyRespDto> getInbodyByUserId(int accountId) {
        List<Inbody> inbodyList = inbodyMapper.findInbodyByAccountId(accountId);

        return inbodyList.stream()
                .map(Inbody::toInbodyRespDto)
                .collect(Collectors.toList());
    }

    public List<SearchInbodyRespDto> getInbodyInformation(int userId) {
        List<Inbody> inbodys = inbodyMapper.findInbodyByUserId(userId);

        return inbodys.stream().map(Inbody::toSearchInbodyRespDto).collect(Collectors.toList());
    }
}