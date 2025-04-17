package com.example.HouseholdAccountBook.Exception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {
    private final Integer statusCode;

    public DataNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }
}
