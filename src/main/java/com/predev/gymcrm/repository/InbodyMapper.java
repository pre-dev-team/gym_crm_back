package com.predev.gymcrm.repository;

import com.predev.gymcrm.entity.Inbody;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InbodyMapper {
    int saveInbody(Inbody inbody);
    List<Inbody> findInbodyByAccountId(int accountId);
    List<Inbody> findInbodyByUserId(int userId);
}