package org.example.service.impl;

import org.example.config.JwtProvider;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        public JwtProvider jwtProvider;

        @Override
        public User findUserByJwtToken(String jwt) throws Exception {
            String email = jwtProvider.getEmailFromJwtToken(jwt);
            User user= findUserByEmail(email);
            return user;
        }

        @Override
        public User findUserByEmail(String email) throws Exception {
            User user = userRepository.findByEmail(email);

            if (user==null){
                throw new Exception("user not found");
            }

            return user;
        }


}
