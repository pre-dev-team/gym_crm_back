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

    @Autowired
    private InbodyMapper inbodyMapper;

    public int addInbody(InbodyReqDto inbodyReqDto) {
        int successCount = 0;
        successCount =inbodyMapper.insertInbody(inbodyReqDto.toInbodyEntity());
        return successCount;
    }

    public List<InbodyRespDto> selectInbodyByUserId(int accountId) {
        List<Inbody> inbodyList = inbodyMapper.findInbodyByAccountId(accountId);
        return inbodyList.stream().map(Inbody::toInbodyRespDto).collect(Collectors.toList());
    }

    public List<SearchInbodyRespDto> selectInbodyInformation(int userId) {
        List<Inbody> inbodys = inbodyMapper.findInbodyByUserId(userId);
        return inbodys.stream().map(Inbody::toSearchInbodyRespDto).collect(Collectors.toList());
    }
}