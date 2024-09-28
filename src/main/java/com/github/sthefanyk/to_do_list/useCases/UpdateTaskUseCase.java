package com.github.sthefanyk.to_do_list.useCases;

import java.util.Optional;

import com.github.sthefanyk.to_do_list.contracts.TaskUpdateInput;
import com.github.sthefanyk.to_do_list.model.Status;
import com.github.sthefanyk.to_do_list.model.Task;
import com.github.sthefanyk.to_do_list.repositories.TaskRepository;

public class UpdateTaskUseCase {
    private TaskRepository repository;

    public UpdateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public void execute(TaskUpdateInput input) {

        if (repository.getById(input.id()) instanceof Task) {
            input.status().ifPresentOrElse(
                status -> {
                    Task task = new Task(input.id(), input.name(), Optional.ofNullable(Status.fromString(status)));
                    repository.save(task);
                }, 
                () -> {
                    Task task = new Task(input.id(), input.name(), null);
                    repository.save(task);
                }
            );   
        }
    }

    
}
