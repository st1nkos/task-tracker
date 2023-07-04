package ru.zhdanov.tasktrackerv2.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zhdanov.tasktrackerv2.auth.entity.User;
import ru.zhdanov.tasktrackerv2.auth.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User get(int id){
        return userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
