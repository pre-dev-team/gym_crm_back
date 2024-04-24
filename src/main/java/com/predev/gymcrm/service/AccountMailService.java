package com.predev.gymcrm.service;

import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.repository.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class AccountMailService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.address}")
    private String fromMailAddress;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.deploy-address}")
    private String serverAddress;

    public Account getAccountByNameAndEmail(String name, String email) {
        return authMapper.findAccountByNameAndEmail(name, email);
    }
    public boolean searchAccountByMail(Account account) {
        boolean result = false;
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        if(account != null) {
            try {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
                helper.setSubject("[PREDEV-GYM] 계정 찾기");
                helper.setFrom(fromMailAddress);
                helper.setTo(account.getEmail());

                StringBuilder mailContent = new StringBuilder();
                mailContent.append("<div>");
                mailContent.append("<h1>PREDEV GYM</h1>");
                mailContent.append("<div>");
                mailContent.append("<h3>귀하의 아이디는 " + account.getUsername() + "입니다</h3>");
                mailContent.append("</div>");
                mailContent.append("</div>");

                mimeMessage.setText(mailContent.toString(), "UTF-8", "html");
                javaMailSender.send(mimeMessage);
                result = true;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return result;
    }
}
