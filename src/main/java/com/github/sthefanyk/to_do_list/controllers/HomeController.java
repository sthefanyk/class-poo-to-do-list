package com.github.sthefanyk.to_do_list.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/")
public class HomeController {

    private List<Task> tasks = new ArrayList<>();

    {
        tasks.add(new Task("Task 1", "Pending"));
        tasks.add(new Task("Task 2", "In Progress"));
        tasks.add(new Task("Task 3", "Completed"));
    }

    
    @GetMapping("/home")    
    public String homePage(Model model) {
        model.addAttribute("tasks", tasks);
        return "home";
    }

    @PostMapping("/task")
    public String createTask(Task task) {

        tasks.add(task);
        return "redirect:/home";
    }
    
    @PostMapping("/task/{id}/complete")
    public String completeTask(@PathVariable String id) {
        
        tasks.stream()
            .filter(task -> task.getId().equals(id))
            .findFirst()
            .ifPresent(task -> {

                switch (task.getStatus()) {
                    case "Completed", "Canceled":
                        task.setStatus("Pending");
                        break;
                    case "Pending", "In Progress":
                        task.setStatus("Completed");
                        break;                
                    default:
                        task.setStatus("Completed");
                        break;
                }
            });
        return "redirect:/home";
    }

    @PostMapping("/task/{id}")
    public String updateTask(@PathVariable String id, Task task) {
        tasks.stream()
            .filter(item -> item.getId().equals(id))
            .findFirst()
            .ifPresent(item -> {
                item.setName(task.getName());
                item.setStatus(task.getStatus());
            });
        return "redirect:/home";
    }

    @PostMapping("/task/{id}/delete")
    public String deleteTask(@PathVariable String id) {
        tasks.removeIf(task -> task.getId().equals(id));
        return "redirect:/home";
    }

    public class Task {
        private UUID id;
        private String name;
        private String status;
        
        public Task(String name, String status) {
            this.id = UUID.randomUUID();
            this.name = name;
            this.status = status;
        }

        public String getId() {
            return id.toString();
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
    }
}
