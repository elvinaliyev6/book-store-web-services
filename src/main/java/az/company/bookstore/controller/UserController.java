package az.company.bookstore.controller;

import az.company.bookstore.request.RequestUser;
import az.company.bookstore.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Tag(name = "User services",description = "user services")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password){
       return userService.checkLogin(username,password);
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody @Valid RequestUser requestUser){
        userService.signUp(requestUser);
    }


}
