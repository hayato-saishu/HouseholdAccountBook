package com.example.HouseholdAccountBook.service.impl;

import com.example.HouseholdAccountBook.Exception.OurException;
import com.example.HouseholdAccountBook.dto.LoginRequest;
import com.example.HouseholdAccountBook.dto.Response;
import com.example.HouseholdAccountBook.entity.User;
import com.example.HouseholdAccountBook.repository.UserRepository;
import com.example.HouseholdAccountBook.service.AuthService;
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
public class AuthServiceImpl implements AuthService {

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
        response.setUserDto(mapperToDto.mapperToUserDto(user));
        response.setStatusCode(200);
        response.setMessage("アカウントを作成しました。");

        return response;
    }

    public Response login(LoginRequest loginRequest) {
        Response response = new Response();

        try {
            User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new OurException("ユーザーが見つかりませんでした。"));
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword())
            );
            MapperToDto mapperToDto = new MapperToDto();
            response.setUserDto(mapperToDto.mapperToUserDto(user));
            response.setStatusCode(200);
            response.setAccessToken(jwtUtils.generateToken(authentication));

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage("ユーザーが見つかりませんでした。");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("ログイン時にエラーが発生しました。");
        }

        return response;
    }
}
