package com.github.sthefanyk.to_do_list.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private List<Task> tasks = new ArrayList<>();

    {
        tasks.add(new Task("Task 1", "Pending"));
        tasks.add(new Task("Task 2", "In Progress"));
        tasks.add(new Task("Task 3", "Completed"));
        tasks.add(new Task("Task 4", "Canceled"));
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
    
    @PostMapping("/task/{name}/complete")
    public String completeTask(@PathVariable String name) {
        
        tasks.stream()
            .filter(task -> task.getName().equals(name))
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

    public class Task {
        private String name;
        private String status;
        
        public Task(String name, String status) {
            this.name = name;
            this.status = status;
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
