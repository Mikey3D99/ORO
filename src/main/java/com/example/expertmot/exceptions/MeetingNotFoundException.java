package com.example.expertmot.exceptions;

public class MeetingNotFoundException extends Exception {
    public MeetingNotFoundException(Long id) {
        super("Could not find Meeting" + id);
    }
}
