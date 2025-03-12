package com.project.redis.service.serviceImpl;

import com.project.redis.model.UserDetails;
import com.project.redis.model.UserResponse;
import com.project.redis.repository.UserRepository;
import com.project.redis.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static UserRepository repository;
    public UserServiceImpl(UserRepository userRepository) {
        repository = userRepository;
    }

    @Override
    public UserResponse addUser(UserDetails userDetails) {
        logger.info("UserServiceImpl :: {}",userDetails);
        UserResponse response = new UserResponse();
        UserDetails details = repository.save(userDetails);
        if (details.getId() > 0) {
            response.setMessage("User Added Successfully");
            response.setStatusCode(200);
            response.setStatus(true);
        } else {
            response.setMessage("Failed");
            response.setStatusCode(400);
            response.setStatus(false);
        }
        logger.info("UserServiceImpl :: {}",response);
        return response;
    }

    @Override
    @CacheEvict(value = "user", key = "#userDetails.getId()")
    public UserResponse updateUser(UserDetails userDetails) {
        logger.info("UserServiceImpl :: {}", userDetails);
        UserResponse response = new UserResponse();

        UserDetails details = repository.save(userDetails);

        if (details.getId() > 0) {
            response.setMessage("User Updated Successfully");
            response.setStatusCode(200);
            response.setStatus(true);
        } else {
            response.setMessage("Update Failed");
            response.setStatusCode(500);
            response.setStatus(false);
        }

        logger.info("UserServiceImpl :: {}", response);
        return response;
    }



    @CacheEvict(value = "user", key = "#userId")
    @Override
    public UserResponse deleteUser(int userId) {
        logger.info("UserServiceImpl :: {}",userId);
        UserResponse response = new UserResponse();
        int result = repository.deleteUser(userId);
        if (result > 0) {
//            deleteUserFromCache(userId);
            response.setMessage("User Deleted Successfully");
            response.setStatusCode(200);
            response.setStatus(true);
        } else {
            response.setMessage("User Not Found");
            response.setStatusCode(204);
            response.setStatus(false);
        }
        logger.info("UserServiceImpl :: {}",response);
        return response;
    }

    @CacheEvict(value = "user", key = "#userId")
    @Override
    public UserResponse activateUser(int userId) {
        logger.info("UserServiceImpl :: {}",userId);
        UserResponse response = new UserResponse();
        int result = repository.activateUser(userId);
        if (result > 0) {
//            deleteUserFromCache(userId);
            response.setMessage("User Activated Successfully");
            response.setStatusCode(200);
            response.setStatus(true);
        } else {
            response.setMessage("User Not Found");
            response.setStatusCode(204);
            response.setStatus(false);
        }
        logger.info("UserServiceImpl :: {}",response);
        return response;
    }

    @Cacheable(value = "user", key = "#userId")
    @Override
    public UserDetails getUser(int userId) {
        logger.info("UserServiceImpl :: {}",userId);
        UserDetails details = repository.findById(userId).orElse(null);

        logger.info("UserServiceImpl :: {}",details);
        return details;
    }

    @Override
    public List<UserDetails> details() {
        logger.info("UserServiceImpl");
        List<UserDetails> details = repository.findByIsDeletedFalse();

        return details;
    }

    @Override
    public List<UserDetails> deletedUsers() {
        logger.info("UserServiceImpl");
        List<UserDetails> details = repository.findByIsDeletedTrue();

        return details;
    }

    @CacheEvict(value = "user", key = "#userId")
    public void deleteUserFromCache(int userId) {
        logger.info("User removed from cache: {}" , userId);
        System.out.println("User removed from cache: " + userId);
    }
}
