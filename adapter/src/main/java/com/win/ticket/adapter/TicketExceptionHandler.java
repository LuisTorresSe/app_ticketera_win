package com.win.ticket.adapter;

import com.win.ApiErrorResponse;
import com.win.ticket.application.exception.NotFoundManagerAtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice

public class TicketExceptionHandler {

    @ExceptionHandler(NotFoundManagerAtException.class)
    public ResponseEntity<ApiErrorResponse> notFoundManagerAtException (NotFoundManagerAtException e) {
        ApiErrorResponse error = new ApiErrorResponse("NOT_FOUND_MANAGER",e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
