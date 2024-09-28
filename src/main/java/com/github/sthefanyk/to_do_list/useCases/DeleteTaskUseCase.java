package com.github.sthefanyk.to_do_list.useCases;

import com.github.sthefanyk.to_do_list.contracts.TaskDeleteInput;
import com.github.sthefanyk.to_do_list.model.Task;
import com.github.sthefanyk.to_do_list.repositories.TaskRepository;

public class DeleteTaskUseCase {
    private TaskRepository repository;

    public DeleteTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public void execute(TaskDeleteInput input) {

        Task task = repository.getById(input.id());

        if (task instanceof Task) {
            repository.delete(task.getId().toString());  
        }
    }
}
