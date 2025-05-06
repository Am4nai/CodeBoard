package com.codeboard.codeboard_backend.service;

import com.codeboard.codeboard_backend.dto.response.LanguageResponseDto;
import com.codeboard.codeboard_backend.model.Language;
import com.codeboard.codeboard_backend.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    public List<LanguageResponseDto> getAllLanguages() {
        return languageRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<LanguageResponseDto> searchLanguagesByName(String name) {
        return languageRepository.findByNameIgnoreCase(name).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private LanguageResponseDto convertToDto(Language language) {
        LanguageResponseDto dto = new LanguageResponseDto();
        dto.setId(language.getId());
        dto.setName(language.getName());
        return dto;
    }
}