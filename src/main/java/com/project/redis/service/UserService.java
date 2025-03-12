package com.project.redis.service;

import com.project.redis.model.UserDetails;
import com.project.redis.model.UserResponse;

import java.util.List;

public interface UserService {

    public UserResponse addUser(UserDetails userDetails);
    public UserResponse updateUser(UserDetails userDetails);
    public UserResponse deleteUser(int userId);
    public UserResponse activateUser(int userId);
    public UserDetails getUser(int userId);
    public List<UserDetails> details();
    public List<UserDetails> deletedUsers();
}
