package com.kosmo.kosmofurniture.exception;

public class NoCartItemException extends RuntimeException{
    public NoCartItemException(String massage) {
        super(massage);
    }
}
