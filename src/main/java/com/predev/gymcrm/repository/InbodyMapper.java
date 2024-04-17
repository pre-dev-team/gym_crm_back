package com.predev.gymcrm.repository;

import com.predev.gymcrm.dto.req.InbodyReqDto;
import com.predev.gymcrm.dto.resp.InbodyRespDto;
import com.predev.gymcrm.entity.Inbody;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InbodyMapper {
    void insertInbody(InbodyReqDto inbodyReqDto);

    InbodyRespDto toInbodyRespDto(Inbody inbody);

    List<Inbody> findInbodyByAccountId(int accountId);
}