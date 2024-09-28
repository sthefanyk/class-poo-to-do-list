package com.github.sthefanyk.to_do_list.contracts;

import java.util.List;

import com.github.sthefanyk.to_do_list.model.Task;

public interface TaskAdapter {
    void create(Task task);
    void save(Task task);
    void delete(String id);
    Task getById(String id);
    List<Task> listAll();
}
