package com.example.todos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PasswordUpdateRequest {
    @NotEmpty
    @Size(min = 8, max = 30, message = "Old password must be at least 8 char long!")
    private String oldPassword;

    @NotEmpty
    @Size(min = 8, max = 30, message = "New password must be at least 8 char long!")
    private String newPassword;

    @NotEmpty
    @Size(min = 8, max = 30, message = "Confirmed password must be at least 8 char long!")
    private String confirmedPassword;

    public PasswordUpdateRequest(String oldPassword, String newPassword, String confirmedPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmedPassword = confirmedPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
