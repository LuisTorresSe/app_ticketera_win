package com.win;

import com.win.ticket.application.exception.NotFoundManagerAtException;
import com.win.ticket.application.exception.NotFoundTicketException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundManagerAtException.class)
    public ResponseEntity<ApiErrorResponse> notFoundManagerAtException (NotFoundManagerAtException e) {
        ApiErrorResponse error = new ApiErrorResponse("NOT_FOUND_MANAGER",e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundTicketException.class)
    public ResponseEntity<ApiErrorResponse> notFoundTicketException (NotFoundTicketException e) {
        ApiErrorResponse error = new ApiErrorResponse("NOT_FOUND_TICKET",e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
