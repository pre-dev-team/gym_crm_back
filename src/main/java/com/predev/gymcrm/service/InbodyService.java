package com.predev.gymcrm.service;

import com.predev.gymcrm.dto.req.InbodyReqDto;
import com.predev.gymcrm.dto.resp.InbodyRespDto;
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

    public InbodyRespDto addInbody(InbodyReqDto inbodyReqDto) {
        // InbodyReqDto를 Inbody로 변환
        Inbody inbody = Inbody.builder()
                .inbodyUrl(inbodyReqDto.getInbodyUrl())
                .weight(inbodyReqDto.getWeight())
                .muscleMass(inbodyReqDto.getMuscleMass())
                .fatMass(inbodyReqDto.getFatMass())
                .build();

        // Inbody를 데이터베이스에 저장
        inbodyMapper.insertInbody(inbodyReqDto);

        // 저장된 Inbody 정보를 읽어와서 InbodyRespDto로 변환
        InbodyRespDto inbodyRespDto = inbodyMapper.toInbodyRespDto(inbody);

        // InbodyRespDto 반환
        return inbodyRespDto;
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
}