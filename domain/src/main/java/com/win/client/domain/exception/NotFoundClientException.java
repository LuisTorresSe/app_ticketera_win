package com.win.client.domain.exception;

public class NotFoundClientException extends RuntimeException {
    public NotFoundClientException(String message) {
        super(message);
    }
}
