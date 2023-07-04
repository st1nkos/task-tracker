package ru.zhdanov.tasktrackerv2.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.zhdanov.tasktrackerv2.auth.entity.User;
import ru.zhdanov.tasktrackerv2.auth.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User get(int id){
        return userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
