package com.devsuperior.demo.servicies.exception;

public class ObjectNotFound extends RuntimeException{
    public ObjectNotFound(String msg){
        super(msg);
    }
}
