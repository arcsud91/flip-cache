package com.arc.exceptions;

public class NotFoundException extends  Exception {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Exception ex) {
        super(ex);
    }

    public NotFoundException(String message, Exception ex) {
        super(message, ex);
    }
}
