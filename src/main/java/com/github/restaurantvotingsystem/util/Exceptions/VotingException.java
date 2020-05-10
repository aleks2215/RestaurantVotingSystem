package com.github.restaurantvotingsystem.util.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "You can change your vote until 11:00 AM")
public class VotingException extends RuntimeException {
    public VotingException(String message) {
        super(message);
    }
}
