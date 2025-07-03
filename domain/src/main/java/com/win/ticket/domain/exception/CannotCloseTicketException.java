package com.win.ticket.domain.exception;

public class CannotCloseTicketException extends RuntimeException {
    public CannotCloseTicketException(String message) {
        super(message);
    }
}
