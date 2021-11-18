package com.ryctabo.demo.secjwt.exception;

/**
 * @author Gustavo Pacheco (ryctabo at gmail.com)
 * @version 1.0-SNAPSHOT
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("The user was not found!");
    }
}
