package com.blogapplication.serviceimpl;

import com.blogapplication.Repo.UserRepository;
import com.blogapplication.entities.User;
import com.blogapplication.payload.UserDto;
import com.blogapplication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Collections;
import java.util.List;
@Component

public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User map = this.modelMapper.map(userDto,User.class);
        User addedUser = this.userRepository.save(map);
        return this.modelMapper.map(addedUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) throws NoResourceFoundException {
        User cat = this.userRepository.findById(userId).orElseThrow(()-> new NoResourceFoundException(HttpMethod.PUT,"Data Not Found"));

        cat.setName(user.getName());
        cat.setEmail(user.getEmail());
        cat.setPassword(user.getPassword());
        cat.setAge(user.getAge());
        cat.setGender(user.getGender());
        User updatedUser = this.userRepository.save(cat);

        return this.modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public UserDto getUserByID(Integer userId) throws NoResourceFoundException {
        User cat = this.userRepository.findById(userId).orElseThrow(()-> new NoResourceFoundException(HttpMethod.GET,"Data Not Found"));
        return this.modelMapper.map(cat,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
       List<User> allUser = this.userRepository.findAll();
       return Collections.singletonList(this.modelMapper.map(allUser, UserDto.class));
    }

    @Override
    public void deleteUser(Integer userId) throws NoResourceFoundException {
        User cat = this.userRepository.findById(userId).orElseThrow(()-> new NoResourceFoundException(HttpMethod.DELETE,"Data Not Found"));
        this.userRepository.delete(cat);
    }
}
