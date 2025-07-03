package com.win.ticket.domain.exception;

public class TicketAlreadyCloseException extends RuntimeException {
    public TicketAlreadyCloseException(String message) {
        super(message);
    }
}
