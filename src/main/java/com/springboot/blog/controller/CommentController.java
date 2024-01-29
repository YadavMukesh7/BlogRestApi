package com.springboot.blog.controller;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("post/{postId}/comment")
    public ResponseEntity<List<CommentDto>> getAllCommentByPostId(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getCommentByPost(postId), HttpStatus.OK);
    }

    @GetMapping("post/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> findCommentById(@PathVariable Long id, @PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getCommentById(id, postId), HttpStatus.OK);
    }

    @PutMapping("post/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @PathVariable Long postId,@Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(id, postId, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("post/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id, @PathVariable Long postId) {

        commentService.deleteComment(id, postId);
        return new ResponseEntity<>("Comment Successfully Deleted", HttpStatus.OK);
    }
}
