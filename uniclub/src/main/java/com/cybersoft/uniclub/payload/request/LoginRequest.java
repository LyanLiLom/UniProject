package com.cybersoft.uniclub.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;

public class LoginRequest {
    @NotBlank(message = "User name không được phép rỗng")
    private String username;
    @Min(value = 3,message = "Password không được ít hơn 3 kí tự")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
