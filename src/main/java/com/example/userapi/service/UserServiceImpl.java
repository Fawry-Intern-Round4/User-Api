package com.example.userapi.service;

import com.example.userapi.dto.UserResponse;
import com.example.userapi.entity.User;
import com.example.userapi.enums.Messages;
import com.example.userapi.mapper.UserMapper;
import com.example.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(Messages.USER_NOT_FOUND.getMessage()));
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse deactivateUser(Long id) {
        User user= userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(Messages.USER_NOT_FOUND.getMessage()));
        user.setEnable(false);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse activateUser(Long id) {
        User user= userRepository
                .findById(id).orElseThrow(() -> new UsernameNotFoundException(Messages.USER_NOT_FOUND.getMessage()));
        user.setEnable(true);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }
}
