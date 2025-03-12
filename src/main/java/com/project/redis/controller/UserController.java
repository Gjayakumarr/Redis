package com.project.redis.controller;

import com.project.redis.model.UserDetails;
import com.project.redis.model.UserResponse;
import com.project.redis.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private static UserService userService;

    public UserController(UserService service) {
        userService=service;
    }

    @PostMapping("/addUser")
    public @ResponseBody UserResponse addUser(@RequestBody UserDetails userDetails) {
        logger.info("UserController - /addUser");
        return userService.addUser(userDetails);
    }

    @PostMapping("/updateUser")
    public @ResponseBody UserResponse updateUser(@RequestBody UserDetails userDetails) {
        logger.info("UserController - /updateUser");
        return userService.updateUser(userDetails);
    }

    @PostMapping("/deleteUser/{userId}")
    public @ResponseBody UserResponse deleteUser(@PathVariable int userId) {
        logger.info("UserController - /deleteUser");
        return userService.deleteUser(userId);
    }

    @PostMapping("/activate/{userId}")
    public @ResponseBody UserResponse activateUser(@PathVariable int userId) {
        logger.info("UserController - /activate");
        return userService.activateUser(userId);
    }

    @GetMapping("/getUserById/{userId}")
    public @ResponseBody UserDetails getUserById(@PathVariable int userId) {
        logger.info("UserController - /getUserById");
        return userService.getUser(userId);
    }

    @GetMapping("/getAllUsers")
    public @ResponseBody List<UserDetails> getAllUsers() {
        logger.info("UserController - /getAllUsers");
        return userService.details();
    }

    @GetMapping("/getDeletedUsers")
    public @ResponseBody List<UserDetails> getDeletedUsers() {
        logger.info("UserController - /getDeletedUsers");
        return userService.deletedUsers();
    }
}
