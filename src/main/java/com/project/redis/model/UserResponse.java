package com.project.redis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class UserResponse {
    private int statusCode;
    private String message;
    private boolean status;
    @JsonIgnore
    private List<UserDetails> userDetails;
    @JsonIgnore
    private Object data;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<UserDetails> getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "UserResponse [statusCode=" + statusCode + ", message=" + message + ", status=" + status
				+ ", userDetails=" + userDetails + ", data=" + data + "]";
	}

    
}
