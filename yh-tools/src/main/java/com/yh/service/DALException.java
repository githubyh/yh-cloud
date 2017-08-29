package com.yh.service;

public class DALException extends RuntimeException {
    private static final long serialVersionUID = 1712068572264547864L;

    public DALException() {
    }

    public DALException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DALException(String message, Throwable cause) {
        super(message, cause);
    }

    public DALException(String message) {
        super(message);
    }

    public DALException(Throwable cause) {
        super(cause);
    }
}