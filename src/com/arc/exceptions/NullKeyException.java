package com.arc.exceptions;

public class NullKeyException extends Exception{

    public NullKeyException() {
        super();
    }

    public NullKeyException(String message) {
        super(message);
    }

    public NullKeyException(Exception ex) {
        super(ex);
    }

    public NullKeyException(String message, Exception ex) {
        super(message, ex);
    }
}
