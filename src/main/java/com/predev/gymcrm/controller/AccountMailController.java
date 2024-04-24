package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.SearchUsernameReqDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.service.AccountMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
public class AccountMailController {

    @Autowired
    AccountMailService accountMailService;

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody SearchUsernameReqDto reqDto) {
        Account account = accountMailService.getAccountByNameAndEmail(reqDto.getName(), reqDto.getEmail());
        return ResponseEntity.ok(accountMailService.searchAccountByMail(account));
    }
}
