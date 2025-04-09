package com.vaggelis.company.service.exceptions;

public class EntityAlreadyExistsException extends Exception{

    public EntityAlreadyExistsException(String name){
        super("Entity already exists");
    }
}
