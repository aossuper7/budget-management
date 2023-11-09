package com.wanted.budgetmanagement.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SUCCESS(HttpStatus.OK, "요청에 성공하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
