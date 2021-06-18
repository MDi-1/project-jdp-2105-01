package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public void saveUser (final User user) {
        userRepository.save(user);
    }

    public Optional<User> findUserById (final Long id) {
        return userRepository.findById(id);
    }

    public void deleteById (final Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> getUsers() {
        List<User> userList = userRepository.findAll();
        return userMapper.mapToUserDtoList(userList);
    }

    public UserDto getUser(Long id) throws UserNotFoundException {
        return userRepository.findById(id).map(userMapper::mapToUserDto)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public void addUser(UserDto userDto) {
        userRepository.save(userMapper.mapToUser(userDto));
    }

    public UserDto updateUser(Long id, UserDto userDto) throws UserNotFoundException {
        return userRepository.findById(id).map(user -> {
            User userUpdated = userMapper.mapToUser(userDto);
            userRepository.save(userUpdated);
            return userMapper.mapToUserDto(userUpdated);}
        ).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public int generateKey(User user) {
        int max = 999999;
        int min = 100000;
        int userKey = (int) (Math.random() * ((max - min) + 1)) + min;
        user.setGeneratedKey(LocalDateTime.now().plusHours(1));
        user.setKey(userKey);
        return userKey;
    }
}
