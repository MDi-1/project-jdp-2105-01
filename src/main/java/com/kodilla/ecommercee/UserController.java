package com.kodilla.ecommercee;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        //do nothing
    }

    @PatchMapping("/{id}/status/")
    public UserDto blockUser(@PathVariable Long id) {
        return new UserDto(2L, 5678, "User2");
    }

    @PatchMapping("/{id}/userKey/")
    public UserDto createRandomUSerKey(@PathVariable Long id) {
        return new UserDto(3L, 7890, "User3");
    }
}
