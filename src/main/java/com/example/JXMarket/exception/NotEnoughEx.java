package com.example.JXMarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughEx extends RuntimeException{
    public NotEnoughEx(Long productId) {
        super("not enough for product " + productId);
    }
}
