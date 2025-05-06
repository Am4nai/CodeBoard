package com.codeboard.codeboard_backend.controller;

import com.codeboard.codeboard_backend.dto.request.PostCreateDto;
import com.codeboard.codeboard_backend.dto.response.PostResponseDto;
import com.codeboard.codeboard_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/all")
    public ResponseEntity<Page<PostResponseDto>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(postService.getAllPosts(page, limit));
    }

    @PostMapping("/create")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostCreateDto request) {
        PostResponseDto response = postService.createPost(request);
        return ResponseEntity.status(201).body(response); // 201 Created
    }
}