package tech.alxinc.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/create")

public class UserController {

    @PostMapping("/")
    public void create(@RequestBody UserModel userModel){
        System.out.println("Created: " + userModel.getName());
    }
}
