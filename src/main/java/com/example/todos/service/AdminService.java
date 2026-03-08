package com.example.todos.service;

import com.example.todos.responses.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
    UserResponse promoteToAdmin(long id);
    void deleteNonAdmin(long id);
}
