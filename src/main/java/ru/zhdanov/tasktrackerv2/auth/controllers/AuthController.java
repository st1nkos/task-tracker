package ru.zhdanov.tasktrackerv2.auth.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.zhdanov.tasktrackerv2.auth.dto.AuthenticationDto;
import ru.zhdanov.tasktrackerv2.auth.dto.UserDto;
import ru.zhdanov.tasktrackerv2.auth.entity.User;
import ru.zhdanov.tasktrackerv2.auth.mappers.UserMapper;
import ru.zhdanov.tasktrackerv2.auth.security.JWTUtil;
import ru.zhdanov.tasktrackerv2.auth.services.RegistrationService;
import ru.zhdanov.tasktrackerv2.auth.utils.UserErrorResponse;
import ru.zhdanov.tasktrackerv2.auth.utils.UserNotCreatedException;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid UserDto userDto) {

        User user = userMapper.toEntity(userDto);
        registrationService.checkUsernameExists(user);

        registrationService.register(user);
        jwtUtil.generateToken(user.getUsername());

        String token = jwtUtil.generateToken(user.getUsername());

        return Map.of("jwt-token", token);

    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(),
                        authenticationDto.getPassword());

        authenticationManager.authenticate(authenticationToken);

        String token = jwtUtil.generateToken(authenticationDto.getUsername());

        return Map.of("jwt-toke", token);
    }


}
