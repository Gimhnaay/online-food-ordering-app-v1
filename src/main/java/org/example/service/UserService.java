package org.example.service;
import org.example.model.User;


    public interface UserService {
        public User findUserByJwtToken(String jwt) throws Exception;

        public User findUserByEmail(String email) throws Exception;

    }


