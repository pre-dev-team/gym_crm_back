package com.predev.gymcrm.exception;

public class RequestInLimitTimeException extends RuntimeException{
    public RequestInLimitTimeException(long leftTime) {
        super("다음요청까지 남은시간: " + leftTime);
    }
}