package com.example.delivery.app.Exception;

public class ItemNotFoundException extends Exception{

    public ItemNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
