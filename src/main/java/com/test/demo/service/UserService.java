package com.test.demo.service;

import com.google.common.hash.Hashing;
import com.test.demo.dto.UserDTO;
import com.test.demo.model.User;
import com.test.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    public UserDTO save(String name, String password) {

        User user = new User(name, hashWithSha256(name + password));
        User saved = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(saved, userDTO);
        return userDTO;
    }

    private String hashWithSha256(String originalString) {
        return Hashing.sha256()
                .hashString(originalString,
                        StandardCharsets.UTF_8).toString();
    }
}
