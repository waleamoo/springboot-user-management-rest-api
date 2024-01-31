package com.techqwerty.springboorestapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.techqwerty.springboorestapi.dto.UserDto;
import com.techqwerty.springboorestapi.entity.User;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}
