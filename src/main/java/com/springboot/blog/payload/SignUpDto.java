package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDto {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, message = "Name should have at least 3 character")
    private String name;
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 3, message = "Username should have at least 3 character")
    private String userName;
    @NotEmpty(message = "Email Should not be empty")
    @Email(message = "Email should be a valid email")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 3, message = "Password should have at least 3 character")
    private String password;
}
