package com.win;

import lombok.Data;

import java.util.Date;

@Data
public class ApiErrorResponse {
    private String errorCode;
    private String message;
    private boolean success;
    public ApiErrorResponse(String errorCode, String message) {
        this.success = false;
        this.errorCode = errorCode;
        this.message = message;
    }
}

