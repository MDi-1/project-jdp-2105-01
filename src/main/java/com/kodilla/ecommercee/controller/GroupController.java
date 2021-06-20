package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/group")
public class GroupController {
    private final GroupService service;
    private final GroupMapper mapper;

    @GetMapping(value = "/all")
    public List<GroupDto> getGroups() {
        List<Group> groups = service.getGroups();
        return mapper.mapToGroupDtoList(groups);
    }

    @GetMapping(value = "getGroup")
    public GroupDto getGroup(@RequestParam Long id) throws GroupNotFoundException {
        return mapper.mapToGroupDto(service.getGroup(id).orElseThrow(GroupNotFoundException::new));
    }

    @PostMapping(value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
        Group group = mapper.mapToGroup(groupDto);
        service.saveGroup(group);
    }

    @PutMapping(value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        Group group = mapper.mapToGroup(groupDto);
        Group savedGroup = service.saveGroup(group);
        return mapper.mapToGroupDto(savedGroup);
    }

    @DeleteMapping("deleteGroup")
    public void deleteGroup(@RequestParam Long id) {
        service.deleteGroup(id);
    }
}