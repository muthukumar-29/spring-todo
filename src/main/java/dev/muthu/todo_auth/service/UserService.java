package dev.muthu.todo_auth.service;

import dev.muthu.todo_auth.model.User;
import dev.muthu.todo_auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

}
