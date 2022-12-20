package com.example.expertmot.exceptions;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException(Long id) {
        super("Could not find client" + id);
    }
}
