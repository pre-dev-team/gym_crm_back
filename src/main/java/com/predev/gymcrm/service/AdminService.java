package com.predev.gymcrm.service;

import com.predev.gymcrm.entity.TrainerReview;
import com.predev.gymcrm.repository.ReviewMapper;
import com.predev.gymcrm.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ReviewMapper reviewMapper;

}
