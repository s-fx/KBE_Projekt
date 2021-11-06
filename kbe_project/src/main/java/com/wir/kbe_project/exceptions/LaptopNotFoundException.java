package com.wir.kbe_project.exceptions;

public class LaptopNotFoundException extends RuntimeException {

    public LaptopNotFoundException(Long id) {
        super("Could not find Laptop " + id);
    }
}
