package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.DTo.UserDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable Long id) throws UserNotFoundException {
        return userService.getUser(id);
    }

    @PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PatchMapping("/{id}/block/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void blockUser(@PathVariable Long id) throws UserNotFoundException {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        user.setBlocked(true);
        userService.saveUser(user);
    }

    @PatchMapping("/{id}/userKey/")
    @ResponseStatus(HttpStatus.CREATED)
    public int createRandomUserKey(@PathVariable Long id) throws UserNotFoundException {
       User user = userService.findUserById(id)
               .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
       int key = userService.generateKey(user);
       userService.saveUser(user);
       return key;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUSer(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) throws UserNotFoundException {
        return userService.updateUser(id, userDto);
    }
}
