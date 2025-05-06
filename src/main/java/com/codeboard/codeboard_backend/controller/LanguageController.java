package com.codeboard.codeboard_backend.controller;

import com.codeboard.codeboard_backend.dto.response.LanguageResponseDto;
import com.codeboard.codeboard_backend.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping("/all")
    public ResponseEntity<List<LanguageResponseDto>> getAllLanguages() {
        return ResponseEntity.ok(languageService.getAllLanguages());
    }

    @GetMapping("/search")
    public ResponseEntity<List<LanguageResponseDto>> searchLanguages(@RequestParam String name) {
        return ResponseEntity.ok(languageService.searchLanguagesByName(name));
    }
}