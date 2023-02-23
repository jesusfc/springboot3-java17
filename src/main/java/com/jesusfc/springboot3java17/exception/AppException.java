package com.jesusfc.springboot3java17.exception;

public class AppException extends RuntimeException{

    public AppException(String message) {
        super(message);
    }
}
