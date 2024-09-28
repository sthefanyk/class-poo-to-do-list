package com.github.sthefanyk.to_do_list.model;

public enum Status {
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Status fromString(String name) {
        String enumName = name.replace(" ", "_").toUpperCase();
        return Status.valueOf(enumName);
    }

    @Override
    public String toString() {
        return getName();
    }
}