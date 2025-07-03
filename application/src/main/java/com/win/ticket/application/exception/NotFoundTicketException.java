package com.win.ticket.application.exception;

public class NotFoundTicketException extends RuntimeException {
    public NotFoundTicketException(String message) {
        super(message);
    }
}
