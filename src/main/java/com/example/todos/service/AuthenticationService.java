package com.example.todos.service;

import com.example.todos.request.AuthenticationRequest;
import com.example.todos.request.RegisterRequest;
import com.example.todos.responses.AuthenticationResponse;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    void register(RegisterRequest input) throws Exception;
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
