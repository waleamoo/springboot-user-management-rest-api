package com.techqwerty.springboorestapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.techqwerty.springboorestapi.dto.UserDto;
import com.techqwerty.springboorestapi.entity.User;
import com.techqwerty.springboorestapi.exception.EmailAlreadyExistsException;
import com.techqwerty.springboorestapi.exception.ResourceNotFoundException;
import com.techqwerty.springboorestapi.repository.UserRepository;
import com.techqwerty.springboorestapi.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    // AllArgsConstructor - automatically creates the constructor using lombok and injects the UserRepository class. No need to use @Autowired annotation since this class has only one argument constructor 
    @Override
    public UserDto createUser(UserDto userDto) {
        // check if the email already exists 
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists for the user");
        }
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        // Optional<User> optionalUser = userRepository.findById(userId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", userId)
        );
        //User user = optionalUser.get();
        return modelMapper.map(user, UserDto.class);
    }
    
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        //return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        // User existingUser = userRepository.findById(user.getId()).get();
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        // return UserMapper.mapToUserDto(updatedUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }
    
    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }

}
