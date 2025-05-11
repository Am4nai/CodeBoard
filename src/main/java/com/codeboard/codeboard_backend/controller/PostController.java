package com.codeboard.codeboard_backend.controller;

import com.codeboard.codeboard_backend.dto.request.PostCreateDto;
import com.codeboard.codeboard_backend.dto.response.PostResponseDto;
import com.codeboard.codeboard_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.codeboard.codeboard_backend.util.JwtUtils;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final JwtUtils jwtUtils;

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

    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostResponseDto>> getUserPosts(@PathVariable String username) {
        // Получаем все посты пользователя по username
        List<PostResponseDto> posts = postService.getUserPostsByUsername(username);
        return ResponseEntity.ok(posts);
    }

    // Удаление поста
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @RequestHeader("Authorization") String authHeader) {

        // Извлекаем username из токена
        String username = extractUsernameFromToken(authHeader);
        String token = authHeader.substring(7);
        // Проверяем права пользователя и удаляем пост
        postService.deletePost(postId, token);

        return ResponseEntity.noContent().build();
    }

    // Вспомогательный метод для извлечения username из токена
    private String extractUsernameFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid or missing Authorization header");
        }

        String token = authHeader.substring(7);
        return jwtUtils.getUsernameFromToken(token);
    }
}