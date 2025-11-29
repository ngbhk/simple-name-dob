package com.example.simple.service;

import com.example.simple.dto.UserDto;
import com.example.simple.entity.User;
import com.example.simple.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto create(UserDto dto) {
        User user = new User(dto.getName(), dto.getDateOfBirth());
        user = userRepository.save(user);
        return toDto(user);
    }

    public List<UserDto> listAll() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(this::toDto);
    }

    public Optional<UserDto> update(Long id, UserDto dto) {
        return userRepository.findById(id).map(u -> {
            u.setName(dto.getName());
            u.setDateOfBirth(dto.getDateOfBirth());
            return toDto(userRepository.save(u));
        });
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto toDto(User u) {
        return new UserDto(u.getId(), u.getName(), u.getDateOfBirth());
    }
}
