package ru.zhdanov.tasktrackerv2.auth.services;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zhdanov.tasktrackerv2.auth.entity.User;
import ru.zhdanov.tasktrackerv2.auth.repository.UserRepository;
import ru.zhdanov.tasktrackerv2.auth.utils.UserNotCreatedException;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    public void checkUsernameExists(User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new UserNotCreatedException("This username is already taken!");
        }
    }

}
