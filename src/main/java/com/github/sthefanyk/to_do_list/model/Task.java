package com.github.sthefanyk.to_do_list.model;

import java.util.Optional;
import java.util.UUID;

public class Task {
    private UUID id;
    private String name;
    private Status status;

    public Task(String id, String name, Optional<Status> status) {
        this.id = UUID.fromString(id);
        this.name = name;
        this.status = status.orElse(Status.PENDING);
    }

    public Task(String name, Optional<Status> status) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.status = status.orElse(Status.PENDING);
    }

    public void changeStatus() {
        switch (this.status) {
            case Status.COMPLETED:
                this.setStatus(Status.PENDING);
                break;               
            default:
                this.setStatus(Status.COMPLETED);
                break;
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
