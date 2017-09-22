package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by Admin1 on 8/7/2017.
 */

@Service
public interface UserService {

    User findUserByEmail(String email);
   // User findByUUID(String uuid);
   User findByUserName(String userName);
    void saveUser(User user);
    //other crud i don't need to write here because jpa repository provides
}
