package com.example.HouseholdAccountBook.service.impl;

import com.example.HouseholdAccountBook.Exception.OurException;
import com.example.HouseholdAccountBook.dto.Response;
import com.example.HouseholdAccountBook.entity.User;
import com.example.HouseholdAccountBook.repository.UserRepository;
import com.example.HouseholdAccountBook.service.UserService;
import com.example.HouseholdAccountBook.util.JWTUtils;
import com.example.HouseholdAccountBook.util.MapperToDto;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    JWTUtils jwtUtils;
    @Autowired
    UserRepository userRepository;
    public Response getUserByToken(String token) {
        Response response = new Response();
        try {
            String email = jwtUtils.getEmail(token);
            User user = userRepository.findByEmail(email).orElseThrow(() -> new OurException("ユーザー情報を取得できませんでした。"));

            response.setStatusCode(200);
            MapperToDto mapperToDto = new MapperToDto();
            response.setUserDto(mapperToDto.mapperToUserDto(user));
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage("ユーザー情報を取得できませんでした。");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("エラーが発生しました。");
        }

        return response;
    }
}
