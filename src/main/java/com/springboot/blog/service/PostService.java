package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);

    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    public PostDto getPostById(Long id);

    public PostDto updatePost(Long id, PostDto postDto);

    public void deletePostById(Long id);
}
