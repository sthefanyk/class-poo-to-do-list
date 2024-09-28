package com.github.sthefanyk.to_do_list.useCases;

import com.github.sthefanyk.to_do_list.contracts.TaskChangeStatusInput;
import com.github.sthefanyk.to_do_list.model.Task;
import com.github.sthefanyk.to_do_list.repositories.TaskRepository;

public class ChangeStatusTaskUseCase {
    private TaskRepository repository;

    public ChangeStatusTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public void execute(TaskChangeStatusInput input) {

        Task task = repository.getById(input.id());

        if (task instanceof Task) {
            task.changeStatus();
            repository.save(task);  
        }
    }

    
}
