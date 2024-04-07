package com.predev.gymcrm.exception;

public class JwtException extends RuntimeException{
    public JwtException() {
        super("유효하지 않은 Jwt토큰입니다.");
    }
}
