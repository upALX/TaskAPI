package tech.alxinc.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        System.out.println("Controller" + request.getAttribute("idUser"));

        var idUser = request.getAttribute("idUser");
        taskModel.setUserId((UUID) idUser);

        var CurrentDate = LocalDateTime.now();

        if(CurrentDate.isAfter(taskModel.getStartAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The START date must be greater than the current date");
        }else if(CurrentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The END date must be greater than the current date");
        }else if (taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The START date must be greater than END date");
        }else{
            var newTask = this.taskRepository.save(taskModel);

            return ResponseEntity.status(HttpStatus.OK).body(newTask);
        }
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var ListTasks = this.taskRepository.findByUserId((UUID) idUser);

        return ListTasks;
    }

    @PutMapping("/{id}")
    public TaskModel UpdateTask(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request){
        var IdUser = request.getAttribute("idUser");

        taskModel.setUserId((UUID) IdUser);

        taskModel.setId(id);

        return this.taskRepository.save(taskModel); 
    }
}
