package com.project.redis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private int statusCode;
    private String message;
    private boolean status;
    @JsonIgnore
    private List<UserDetails> userDetails;
    @JsonIgnore
    private Object data;

}
