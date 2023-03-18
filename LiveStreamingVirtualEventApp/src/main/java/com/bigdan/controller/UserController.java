package com.bigdan.controller;


import com.bigdan.entity.Event;
import com.bigdan.entity.User;
import com.bigdan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody User user, Errors errors) {
        String response = "";
        if (errors.hasErrors())
        {
            response  = errors.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.ok(response);
        }
        else
        {
            response = userService.registerNewUser(user);
            return ResponseEntity.ok(response);
        }

    }

    @PostMapping({"/registerNewAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> registerNewAdmin(@Valid @RequestBody User user,Errors errors) {
        String response = "";
        if (errors.hasErrors())
        {
            response  = errors.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.ok(response);
        }
        else
        {
            response = userService.registerNewAdmin(user);
            return ResponseEntity.ok(response);
        }

    }

    @GetMapping({"/getProfile/{userId}"})
    @PreAuthorize("hasAnyRole('Admin','Organizer')")
    public ResponseEntity<User> getProfile(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PutMapping("updateUser/{userId}")
    @PreAuthorize("hasAnyRole('Admin', 'Organizer')")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @Valid @RequestBody User user, Errors errors) {
        user.setUserEmail(userId);
        if (errors.hasErrors())
        {
            String response  = errors.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(","));

            return ResponseEntity.ok(response);
        }
        else {
            return ResponseEntity.ok(userService.updateUser(user));
        }
    }

    @GetMapping({"/getAllUser"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok( userService.getAllUser() );
    }


//    Test API
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('Organizer')")
    public String forUser(){
        return "This URL is only accessible to the Organizer";
    }
}
