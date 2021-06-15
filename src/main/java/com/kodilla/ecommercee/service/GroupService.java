package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class GroupService {

    private final GroupRepository repository;

    public List<Group> getAllGroups() {
        return repository.findAll();
    }

    public Optional<Group> getGroupById(Long id) {
        return repository.findById(id);
    }

    public Group saveGroup(final Group group) {
        return repository.save(group);
    }

    public void deleteGroup(final Long id) {
        repository.deleteById(id);
    }
}
