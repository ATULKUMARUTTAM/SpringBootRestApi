package com.atuluttam.SpringBootBeanValidationandException.Error;

import org.springframework.stereotype.Component;

@Component
public class StudentException extends RuntimeException{
    public StudentException() {

    }

    public StudentException(String message) {
        super("Student Exception");
    }
}
