package com.codeboard.codeboard_backend.service;

import com.codeboard.codeboard_backend.dto.response.TagResponseDto;
import com.codeboard.codeboard_backend.model.Tag;
import com.codeboard.codeboard_backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<TagResponseDto> getAllTags() {
        return tagRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TagResponseDto> searchTagsByName(String name) {
        return tagRepository.findByNameIgnoreCase(name).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    private TagResponseDto convertToDto(Tag tag) {
        TagResponseDto dto = new TagResponseDto();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        return dto;
    }
}