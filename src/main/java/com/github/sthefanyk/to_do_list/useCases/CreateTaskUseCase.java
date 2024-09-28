package com.github.sthefanyk.to_do_list.useCases;

import java.util.Optional;

import com.github.sthefanyk.to_do_list.contracts.TaskCreateInput;
import com.github.sthefanyk.to_do_list.model.Status;
import com.github.sthefanyk.to_do_list.model.Task;
import com.github.sthefanyk.to_do_list.repositories.TaskRepository;

public class CreateTaskUseCase {
    private TaskRepository repository;

    public CreateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public void execute(TaskCreateInput input) {

        input.status().ifPresentOrElse(
            status -> {
                Task task = new Task(input.name(), Optional.ofNullable(Status.fromString(status)));
                repository.create(task);
            }, 
            () -> {
                Task task = new Task(input.name(), null);
                repository.create(task);
            }
        );
    }

    
}
