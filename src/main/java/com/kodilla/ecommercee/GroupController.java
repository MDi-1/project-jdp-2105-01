package com.kodilla.ecommercee;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/group")
public class GroupController {

        @GetMapping (value = "getGroups")
        public List<String> getGroups() {
            return new ArrayList<>();
        }

        @GetMapping (value = "getGroup")
        public void getGroup() {
        }

        @PostMapping (value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
        public void createGroup() {
        }

        @PutMapping (value = "updateGroup")
        public void updateGroup() {
        }

        @DeleteMapping("deleteGroup")
        public void deleteGroup() {
        }
}