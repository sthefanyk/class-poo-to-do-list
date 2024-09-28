package com.github.sthefanyk.to_do_list.useCases;

import com.github.sthefanyk.to_do_list.contracts.TaskFindAllOutput;
import com.github.sthefanyk.to_do_list.repositories.TaskRepository;

public class ListAllTaskUseCase {
    private TaskRepository repository;

    public ListAllTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskFindAllOutput execute() {
        TaskFindAllOutput output = new TaskFindAllOutput(repository.listAll());
        return output;
    }
}
