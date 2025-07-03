package com.win;

import lombok.Data;

import java.util.Date;

@Data
public class ApiErrorResponse {
    private String errorCode;
    private String message;
    private Date timestamp;

    public ApiErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = new Date();
    }
}

