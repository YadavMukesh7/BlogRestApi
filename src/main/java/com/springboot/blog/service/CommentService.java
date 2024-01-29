package com.springboot.blog.service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(Long postId, CommentDto commentDto);

    public List<CommentDto> getCommentByPost(Long postId);

    public CommentDto getCommentById(Long id, Long postId);

    public CommentDto updateComment(Long id, Long postId, CommentDto commentRequest);

    public void deleteComment(Long id, Long postId);

}
