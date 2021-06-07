package com.kodilla.ecommercee;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/group")
public class GroupController {

    @GetMapping(value = "getGroups")
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @GetMapping(value = "getGroup")
    public GroupDto getGroup(int id) {
        return new GroupDto(id, "Group");
    }

    @PostMapping(value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(GroupDto groupDto) {
    }

    @PutMapping(value = "updateGroup")
    public GroupDto updateGroup(GroupDto groupDto) {
        return new GroupDto(1, "Group");
    }

    @DeleteMapping("deleteGroup")
    public void deleteGroup(int id) {
    }


}