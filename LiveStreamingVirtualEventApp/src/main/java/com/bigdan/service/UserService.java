package com.bigdan.service;


import com.bigdan.entity.Event;
import com.bigdan.entity.Role;
import com.bigdan.entity.User;
import com.bigdan.repository.RoleDao;
import com.bigdan.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {
 
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("Organizer");
        userRole.setRoleDescription("Organizer role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserEmail("admin@gmail.com");
        adminUser.setUserPassword(getEncodedPassword("admin123"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setCompany("LigueyRekk");
        adminUser.setJobposition("Informaticien");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User manageruser = new User();
        manageruser.setUserEmail("bigdan28@gmail.com");
        manageruser.setUserPassword(getEncodedPassword("big123"));
        manageruser.setUserFirstName("Big");
        manageruser.setUserLastName("Dan");
        manageruser.setCompany("SarayaTech");
        manageruser.setJobposition("Project manager");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        manageruser.setRole(userRoles);
        userDao.save(manageruser);
    }

    public String registerNewUser(User user) {
        Role role = roleDao.findById("Organizer").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        userDao.save(user);
        return user.getUserEmail();
    }
    public String registerNewAdmin(User user) {
        Role role = roleDao.findById("Admin").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        userDao.save(user);
        return user.getUserEmail();
    }


    public User getUserProfile(String userId){
        User user = userDao.findById(userId).get();
        return user;
    }

    public String updateUser(User user) {
        User existingUser = userDao.findById(user.getUserEmail()).orElse(null);
        if (existingUser != null) {
            existingUser.setUserFirstName(user.getUserFirstName());
            existingUser.setUserLastName(user.getUserLastName());
            existingUser.setJobposition(user.getJobposition());
            existingUser.setCompany(user.getCompany());
            existingUser.setUserPassword(user.getUserPassword());

            // Update other fields as needed
            userDao.save(existingUser);
            return existingUser.getUserEmail();
        }
        return null;
    }

    public List<User> getAllUser(){
        return userDao.findAll();
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}