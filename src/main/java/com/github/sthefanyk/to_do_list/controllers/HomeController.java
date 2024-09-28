package com.github.sthefanyk.to_do_list.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.sthefanyk.to_do_list.contracts.TaskChangeStatusInput;
import com.github.sthefanyk.to_do_list.contracts.TaskCreateInput;
import com.github.sthefanyk.to_do_list.contracts.TaskDeleteInput;
import com.github.sthefanyk.to_do_list.contracts.TaskUpdateInput;
import com.github.sthefanyk.to_do_list.model.Task;
import com.github.sthefanyk.to_do_list.repositories.TaskRepository;
import com.github.sthefanyk.to_do_list.useCases.ChangeStatusTaskUseCase;
import com.github.sthefanyk.to_do_list.useCases.CreateTaskUseCase;
import com.github.sthefanyk.to_do_list.useCases.DeleteTaskUseCase;
import com.github.sthefanyk.to_do_list.useCases.ListAllTaskUseCase;
import com.github.sthefanyk.to_do_list.useCases.UpdateTaskUseCase;

@Controller
@RequestMapping("/")
public class HomeController {

    private TaskRepository repository = new TaskRepository();
    private ListAllTaskUseCase listAllUseCase = new ListAllTaskUseCase(repository);
    private CreateTaskUseCase createUseCase = new CreateTaskUseCase(repository);
    private UpdateTaskUseCase updateTaskUseCase = new UpdateTaskUseCase(repository);
    private ChangeStatusTaskUseCase changeStatusTaskUseCase = new ChangeStatusTaskUseCase(repository);
    private DeleteTaskUseCase deleteTaskUseCase = new DeleteTaskUseCase(repository);

    @GetMapping("/home")    
    public String homePage(Model model) {
        List<Task> tasks = listAllUseCase.execute().tasks();
        model.addAttribute("tasks", tasks);

        return "home";
    }

    @PostMapping("/task")
    public String createTask(TaskCreateInput task) {
        createUseCase.execute(task);

        return "redirect:/home";
    }
    
    @PostMapping("/task/{id}/complete")
    public String completeTask(@PathVariable String id) {
        changeStatusTaskUseCase.execute(new TaskChangeStatusInput(id));

        return "redirect:/home";
    }

    @PostMapping("/task/{id}")
    public String updateTask(@PathVariable String id, TaskUpdateInput task) {
        TaskUpdateInput input = new TaskUpdateInput(id, task.name(), task.status());
        updateTaskUseCase.execute(input);

        return "redirect:/home";
    }

    @PostMapping("/task/{id}/delete")
    public String deleteTask(@PathVariable String id) {
        deleteTaskUseCase.execute(new TaskDeleteInput(id));

        return "redirect:/home";
    }
}
