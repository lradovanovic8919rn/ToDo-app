package com.example.todos.service;

import com.example.todos.entities.User;
import com.example.todos.request.PasswordUpdateRequest;
import com.example.todos.responses.UserResponse;

public interface UserService {
    UserResponse getUserInfo();
    void deleteUser();
    void updatePassword(PasswordUpdateRequest passwordUpdateRequest);
}
