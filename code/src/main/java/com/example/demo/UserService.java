package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public user save(user u){ return userRepository.save(u);   }

    public user find(String name){ return userRepository.findByUserName(name);  }


}
