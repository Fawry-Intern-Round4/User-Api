package com.example.userapi.mapper;

import com.example.userapi.dto.UserResponse;
import com.example.userapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "enable", source = "enable")
    @Mapping(target = "role", source = "role")
    UserResponse toUserResponse(User user);
}
