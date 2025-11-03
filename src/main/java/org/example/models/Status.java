package org.example.models;

public enum Status {
    NOT_DONE("Inte klart"),
    DONE("Klart");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}