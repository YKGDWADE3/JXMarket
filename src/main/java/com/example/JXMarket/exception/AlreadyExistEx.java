package com.example.JXMarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistEx extends RuntimeException{
    public AlreadyExistEx(Long id) {
        super("object with id = " + id + "already exist");
    }
}
