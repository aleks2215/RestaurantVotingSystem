package com.github.restaurantvotingsystem.util.Exceptions;

public class IllegalRequestDataException extends RuntimeException {
    public IllegalRequestDataException(String msg) {
        super(msg);
    }
}