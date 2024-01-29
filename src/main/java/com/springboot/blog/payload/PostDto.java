package com.springboot.blog.payload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class PostDto {
    private Long id;
    @NotEmpty(message = "Post should not be empty")
    @Size(min = 2,message = "Post Title should have at list 2 character")
    private String title;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 10,message = "Description should have at least 10 character")
    private String description;
    @NotEmpty(message = "Content should not be blank")
    private String content;
//    @NotEmpty(message = "comments can not be blank")
    private Set<CommentDto> comments = new HashSet<>();
}
