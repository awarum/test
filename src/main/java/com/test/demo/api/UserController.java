package com.test.demo.api;

import com.test.demo.dto.UserDTO;
import com.test.demo.service.UserService;
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

    @RequestMapping(value="/users", method = RequestMethod.POST)
    public UserDTO save(
            @RequestBody SaveUserRequest saveUserRequest) {
        return this.save(saveUserRequest.getName(), saveUserRequest.getPassword());
    }


    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<UserDTO> list() {
        return userService.findAll();
    }

    // TODO: discuss this line
    // This operation could be exposed in restAPI with 2 RequestParams but password is sensitive
    public UserDTO save(String name, String password) {
        return userService.save(name, password);
    }



}
