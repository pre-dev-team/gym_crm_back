package com.predev.gymcrm.controller;

import com.predev.gymcrm.dto.req.SearchUsernameReqDto;
import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.jwt.JwtProvider;
import com.predev.gymcrm.service.AccountMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/mail")
public class AccountMailController {

    @Autowired
    AccountMailService accountMailService;

    @PostMapping("/send")
    public ResponseEntity<?> send(HttpServletRequest request, @RequestBody SearchUsernameReqDto reqDto, HttpServletResponse response) {
        Account account = accountMailService.getAccountByNameAndEmail(reqDto.getName(), reqDto.getEmail());
        accountMailService.searchAccountByMail(account);
        request.getSession().setAttribute("date", new Date());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
