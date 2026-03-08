package com.example.todos.service;

import com.example.todos.entities.Authority;
import com.example.todos.entities.User;
import com.example.todos.repositories.UserRepository;
import com.example.todos.request.PasswordUpdateRequest;
import com.example.todos.responses.UserResponse;
import com.example.todos.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, FindAuthenticatedUser findAuthenticatedUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserInfo() {

        User user = findAuthenticatedUser.getAuthenticatedUser();

        return new UserResponse(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getAuthorities().stream().map(auth -> (Authority) auth).toList()
        );
    }

    @Override
    public void deleteUser() {
        User user = findAuthenticatedUser.getAuthenticatedUser();

        if(isLastAdmin(user)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin cannot delete itself");
        }

        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest) {
        User user = findAuthenticatedUser.getAuthenticatedUser();

        if(!isOldPasswordCorrect(user.getPassword(), passwordUpdateRequest.getOldPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password is incorect!");
        }

        if(!isNewPasswordConfirmed(passwordUpdateRequest.getNewPassword(), passwordUpdateRequest.getConfirmedPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New passwords do not match!");
        }

        if(!isNewPasswordDifferent(passwordUpdateRequest.getOldPassword(), passwordUpdateRequest.getNewPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old and new passwords must be different!");
        }

        user.setPassword(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
        userRepository.save(user);
    }

    private boolean isOldPasswordCorrect(String currentPass, String oldPass){
        System.out.print(passwordEncoder.matches(currentPass, oldPass));
        return passwordEncoder.matches(oldPass, currentPass);
    }

    private boolean isNewPasswordConfirmed(String newPass, String newPassConfirmation){
        return newPass.equals(newPassConfirmation);
    }

    private boolean isNewPasswordDifferent(String oldPass, String newPass){
        return !oldPass.equals(newPass);
    }

    private boolean isLastAdmin(User user){
        boolean isAdmin = user.getAuthorities().stream().anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()));

        if(isAdmin){
            long adminCount = userRepository.countAdmins();
            return adminCount <= 1;
        }

        return false;
    }
}
