package com.jesusfc.springboot3java17.exception;

/**
 * @author jesusfc
 * Created on mar 2023
 */
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
