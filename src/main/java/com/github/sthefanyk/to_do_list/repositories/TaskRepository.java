package com.github.sthefanyk.to_do_list.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.sthefanyk.to_do_list.contracts.TaskAdapter;
import com.github.sthefanyk.to_do_list.model.Task;

public class TaskRepository implements TaskAdapter {

    List<Task> tasks = new ArrayList<>();

    @Override
    public void create(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> listAll() {
        return tasks;
    }

    @Override
    public void save(Task newTask) {
        tasks.forEach(task -> {
            if (task.getId().equals(newTask.getId())) {
                task.setName(newTask.getName());
                task.setStatus(newTask.getStatus());
            }
        });
    }

    @Override
    public Task getById(String id) {
        for (Task task : tasks) {
            if (task.getId().toString().equals(id)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
        tasks.removeIf(task -> task.getId().toString().equals(id));
    }    
}
