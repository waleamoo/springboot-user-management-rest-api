package com.techqwerty.springboorestapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techqwerty.springboorestapi.entity.User;
import com.techqwerty.springboorestapi.repository.UserRepository;
import com.techqwerty.springboorestapi.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    // AllArgsConstructor - automatically creates the constructor using lombok and injects the UserRepository class. No need to use @Autowired annotation since this class has only one argument constructor 
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }
    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
