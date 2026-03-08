package com.example.todos.controller;

import com.example.todos.repositories.UserRepository;
import com.example.todos.responses.UserResponse;
import com.example.todos.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
@RestController
@Tag(name = "Admin REST API endpoints!")
public class AdminController {
    private final AdminService adminService;


    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Get all users")
    public List<UserResponse> getAllUsers(){
        return adminService.getAllUsers();
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/role")
    @Operation(summary = "Upgrade users role to admin!")
    public UserResponse promoteToAdmin(@PathVariable @Min(1) long id){
        return adminService.promoteToAdmin(id);
    }
    @Operation(summary = "Deleta a non admin user!")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletUser(@PathVariable @Min(1) long id){
        adminService.deleteNonAdmin(id);
    }

}
