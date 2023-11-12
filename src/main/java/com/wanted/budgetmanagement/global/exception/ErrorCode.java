package com.wanted.budgetmanagement.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SUCCESS(HttpStatus.OK, "요청에 성공하였습니다."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "액세스 토큰이 만료 되었습니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "토큰이 올바르지 않습니다."),
    EXIST_EMAIL(HttpStatus.CONFLICT, "이미 있는 이메일 입니다.");


    private final HttpStatus httpStatus;
    private final String message;

}
