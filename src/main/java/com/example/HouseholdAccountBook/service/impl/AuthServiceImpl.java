package com.example.HouseholdAccountBook.service.impl;

import com.example.HouseholdAccountBook.dto.LoginRequest;
import com.example.HouseholdAccountBook.dto.Response;
import com.example.HouseholdAccountBook.entity.User;
import com.example.HouseholdAccountBook.repository.UserRepository;
import com.example.HouseholdAccountBook.util.JWTUtils;
import com.example.HouseholdAccountBook.util.MapperToDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtils jwtUtils;

    public Response registerUser(@Valid User user) {
        Response response = new Response();

        if (userRepository.existsByEmail(user.getEmail())) {
            response.setStatusCode(403);
            response.setMessage("メールアドレスが既に使われています。");
            return response;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        MapperToDto mapperToDto = new MapperToDto();
        response.setStatusCode(200);
        response.setMessage("アカウントを作成しました。");
        response.setUserDto(mapperToDto.mapperToUserDto(user));

        return response;
    }

    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword())
        );
        return jwtUtils.generateToken(authentication);
    }
}
