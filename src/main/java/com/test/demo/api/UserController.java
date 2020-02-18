package com.test.demo.api;

import com.test.demo.dto.UserDTO;
import com.test.demo.service.UserService;
import com.test.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public UserDTO save(
            @RequestBody SaveUserRequest saveUserRequest) {
        return this.save(saveUserRequest.getName(), saveUserRequest.getPassword());
    }


    @RequestMapping(value="/list", method = RequestMethod.GET)
    public List<User> list() {
        return userService.findAll();
    }
    public UserDTO save(String name, String password) {
        return userService.save(name, password);
    }



}
