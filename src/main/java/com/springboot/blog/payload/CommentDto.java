package com.springboot.blog.payload;

import com.springboot.blog.entity.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, message = "Name should have at least 3 character ")
    private String name;
    @NotEmpty(message = "Email Should not be empty")
    @Email(message = "Email should be a valid email")
    private String email;
    @NotEmpty(message = "Comment should not be blank")
    @Size(min = 10, message = "Comment should have at least 10 character")
    private String body;
}
