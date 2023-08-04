package com.level.common.exception;

public class CustomNormalException extends RuntimeException{
    public CustomNormalException() {
        super();
    }
    public CustomNormalException(String message) {
        super(message);
    }
    public CustomNormalException(String message, Throwable cause) {
        super(message, cause);
    }
    public CustomNormalException(Throwable cause) {
        super(cause);
    }
    protected CustomNormalException(String message, Throwable cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
