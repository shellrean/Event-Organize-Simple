package com.shellrean.event.organize.exception;

public class EmailTakenException extends RuntimeException {
    public EmailTakenException(String message) {
        super(message);
    }
}
