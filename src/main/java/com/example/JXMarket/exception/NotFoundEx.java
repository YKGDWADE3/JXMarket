package com.example.JXMarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundEx extends RuntimeException {
    public NotFoundEx(long id) {
        super("could not find object which id = " + id + ".");
    }

    public NotFoundEx(String name) {
        super("could not find object which name = " + name + ".");
    }
}
