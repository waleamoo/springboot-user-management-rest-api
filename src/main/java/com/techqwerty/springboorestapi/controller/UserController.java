package com.techqwerty.springboorestapi.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techqwerty.springboorestapi.dto.UserDto;
import com.techqwerty.springboorestapi.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    
    private UserService userService;
    
    // REST APIs
    // POST: http://localhost:8081/api/users 
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // GET: http://localhost:8081/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // GET: http://localhost:8081/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    // PUT: http://localhost:8081/api/users/1
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user){
        user.setId(userId);
        UserDto updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    // DELETE: http://localhost:8081/api/users/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId); 
        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }

    // Handling exception that is specific to UserController 
    // @ExceptionHandler(ResourceNotFoundException.class)
    // public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
    //     ErrorDetails errorDetails = new ErrorDetails(
    //         LocalDateTime.now(),
    //         exception.getMessage(), 
    //         webRequest.getDescription(false),
    //         "USER_NOT_FOUND"
    //     );
    //     return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    // }
}
