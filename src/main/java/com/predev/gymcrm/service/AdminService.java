package com.predev.gymcrm.service;

import com.predev.gymcrm.repository.ReviewMapper;
import com.predev.gymcrm.repository.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private ReviewMapper reviewMapper;

}
