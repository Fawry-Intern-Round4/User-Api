package com.example.userapi.mapper;

import com.example.userapi.dto.UserResponse;
import com.example.userapi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
}
