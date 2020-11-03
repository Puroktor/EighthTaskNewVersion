package ru.vsu.cs.skofenko.consoleVersion;

public class DoublePathException extends Exception {
    private String ofWhat;

    public String getOfWhat() {
        return ofWhat;
    }

    public DoublePathException(String ofWhat){
        this.ofWhat=ofWhat;
    }
}
