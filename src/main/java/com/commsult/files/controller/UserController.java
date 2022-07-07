package com.commsult.files.controller;

import com.commsult.files.model.User;
import com.commsult.files.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "https://stockapp-projecttest.vercel.app/", allowCredentials = "true")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception{
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<List> checkUser(@RequestBody User user) throws Exception{
        return ResponseEntity.ok(userService.checkUser(user));
    }

    @GetMapping("/user")
    public ResponseEntity<List> getUser(@RequestBody User user) throws Exception{
        return ResponseEntity.ok(userService.checkUser(user));
    }
}
