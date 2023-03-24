package com.bigdan.controller;


import com.bigdan.entity.Event;
import com.bigdan.entity.File;
import com.bigdan.entity.User;
import com.bigdan.repository.FileDao;
import com.bigdan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileDao fileDao;

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

    @PutMapping("updateUserRole/{userId}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<String> updateUserRole(@PathVariable String userId, @RequestBody User user) {

        user.setUserEmail(userId);


            return ResponseEntity.ok(userService.updateUserRole(user));

    }

    @DeleteMapping({"/delUser/{userId}"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteMeeting(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping({"/getAllUser"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok( userService.getAllUser() );
    }


//    Add file (photos, video,...)

//    public String addNewEvent(Event event, String userId){
//        User user = userDao.findById(userId).orElseThrow(()-> new IllegalArgumentException("Invalid userId"));
//        event.setUser(user);
//        event.setPublish(false);
//        Event evt = eventDao.save(event);
//        return evt.getEventName();
//    }

    @PostMapping(value = {"/addNewFile"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('Admin', 'Organizer')")
    public String addNewFile(@RequestBody MultipartFile[] myfile ){
        try{
            Set<File> files = uploadFile(myfile);
            fileDao.saveAll(files);

            return "File saved succesfuly";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Set<File> uploadFile(MultipartFile[] multipartFiles) throws IOException {

        Set<File> files =  new HashSet<>();
        for (MultipartFile fichier: multipartFiles ){
            File file = new File(fichier.getOriginalFilename(), fichier.getContentType(), fichier.getBytes());
            files.add(file);
        }

        return files;
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
