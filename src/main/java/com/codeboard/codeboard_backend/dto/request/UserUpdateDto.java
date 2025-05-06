package com.codeboard.codeboard_backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDto {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Size(min = 3, max = 100, message = "Bio must be between 3 and 100 characters")
    private String bio;

    @Size(max = 200, message = "Profile picture URL must not exceed 200 characters")
    private String profilePictureUrl;
}