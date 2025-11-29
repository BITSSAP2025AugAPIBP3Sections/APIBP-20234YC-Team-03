package org.apibp.dwellin.exception;

public class BookingConflictException extends RuntimeException {
    public BookingConflictException(String msg) {
        super(msg);
    }
}
