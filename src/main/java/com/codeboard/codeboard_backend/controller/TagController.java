package com.codeboard.codeboard_backend.controller;

import com.codeboard.codeboard_backend.dto.response.TagResponseDto;
import com.codeboard.codeboard_backend.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping("/all")
    public ResponseEntity<List<TagResponseDto>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @GetMapping("/search")
    public ResponseEntity<List<TagResponseDto>> searchTags(@RequestParam String name) {
        return ResponseEntity.ok(tagService.searchTagsByName(name));
    }
}