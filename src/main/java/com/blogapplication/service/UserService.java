package com.blogapplication.service;

import com.blogapplication.entities.User;
import com.blogapplication.payload.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@Service
public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId) throws Exception;
    UserDto getUserByID(Integer userId) throws NoResourceFoundException;
    List<UserDto> getAllUser();
    void deleteUser(Integer userId) throws NoResourceFoundException;

}
