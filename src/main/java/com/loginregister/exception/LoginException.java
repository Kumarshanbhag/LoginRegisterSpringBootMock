package com.loginregister.exception;

public class LoginException extends RuntimeException {
    public enum Type {
        DUPLICATE_ENTRY, USER_NOT_PRESENT
    }

    public Type type;
    public String message;

    public LoginException(String message, Type type) {
        this.message = message;
        this.type = type;
    }
}
