package tech.alxinc.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        System.out.println("Controller" + request.getAttribute("idUser"));
        var idUser = request.getAttribute("idUser");
        taskModel.setUserId((UUID) idUser);
        var newTask = this.taskRepository.save(taskModel);

        return newTask;
    }
}
