package com.vaggelis.company.service.exceptions;

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException(Class<?> entityClass, Long id){
        super("Entity " + entityClass.getSimpleName() + " with id " + id + " not found");
    }
}
