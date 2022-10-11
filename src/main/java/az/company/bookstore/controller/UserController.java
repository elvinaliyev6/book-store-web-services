package az.company.bookstore.controller;

import az.company.bookstore.request.RequestUser;
import az.company.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password){
       return userService.checkLogin(username,password);
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody RequestUser requestUser){
        userService.signUp(requestUser);
    }


}
