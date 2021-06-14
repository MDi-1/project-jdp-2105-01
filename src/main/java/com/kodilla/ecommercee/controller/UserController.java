package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.DTo.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.UserService;
import com.kodilla.ecommercee.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/getUser/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable Long userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PatchMapping("/block/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void blockUser(@PathVariable Long userId) throws UserNotFoundException {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
        user.setBlocked(true);
        userService.saveUser(user);
    }

    @PatchMapping("/createUserKey/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public int createRandomUserKey(@PathVariable Long userId) throws UserNotFoundException {
       User user = userService.findUserById(userId)
               .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
       int key = userService.generateKey(user);
       userService.saveUser(user);
       return key;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUSer(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

    @PutMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) throws UserNotFoundException {
        return userService.updateUser(userId, userDto);
    }
}
