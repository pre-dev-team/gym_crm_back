package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.SearchUsernameReqDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.service.AccountMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/mail")
public class AccountMailController {

    @Autowired
    AccountMailService accountMailService;

    @PostMapping("/send")
    public ResponseEntity<?> send(HttpServletRequest request, @RequestBody SearchUsernameReqDto reqDto) {
        Account account = accountMailService.getAccountByNameAndEmail(reqDto.getName(), reqDto.getEmail());
        accountMailService.searchAccountByMail(account);
        request.getSession().setAttribute("timer", new Date());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/find/password")
    public ResponseEntity<?> findPassword() {
        return ResponseEntity.ok(null);
    }
}
