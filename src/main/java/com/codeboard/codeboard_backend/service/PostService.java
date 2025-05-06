package com.codeboard.codeboard_backend.service;

import com.codeboard.codeboard_backend.dto.request.PostCreateDto;
import com.codeboard.codeboard_backend.dto.response.PostResponseDto;
import com.codeboard.codeboard_backend.model.enums.VisibilityEnum;
import com.codeboard.codeboard_backend.model.Post;
import com.codeboard.codeboard_backend.model.Tag;
import com.codeboard.codeboard_backend.model.Language;
import com.codeboard.codeboard_backend.model.User;
import com.codeboard.codeboard_backend.repository.PostRepository;
import com.codeboard.codeboard_backend.repository.TagRepository;
import com.codeboard.codeboard_backend.repository.LanguageRepository;
import com.codeboard.codeboard_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;

    public Page<PostResponseDto> getAllPosts(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return postRepository.findAll(pageable).map(this::convertToDto);
    }

    public PostResponseDto createPost(PostCreateDto request) {
        // Находим пользователя (автора)
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Находим язык программирования
        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new IllegalArgumentException("Language not found"));

        // Находим теги
        Set<Tag> tags = new HashSet<>();
        if (request.getTagIds() != null && !request.getTagIds().isEmpty()) {
            tags.addAll(tagRepository.findAllById(request.getTagIds())); // Прямое добавление
        }

        // Создаем новый пост
        Post post = new Post();
        post.setUser(user);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setTags(tags); // Установка коллекции
        post.setLanguage(language);
        post.setVisibilityEnum(VisibilityEnum.valueOf(request.getVisibility().toUpperCase())); // Безопасное преобразование
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        // Сохраняем пост в базе
        Post savedPost = postRepository.save(post);

        // Возвращаем DTO с данными созданного поста
        return convertToDto(savedPost);
    }

    private PostResponseDto convertToDto(Post post) {
        PostResponseDto dto = new PostResponseDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthorUsername(post.getUser().getUsername());
        dto.setLanguageName(post.getLanguage().getName());
        dto.setTags(post.getTags().stream()
                .map(Tag::getName)
                .toList()); // Преобразуем коллекцию тегов в список названий
        dto.setVisibility(post.getVisibilityEnum().name());
        dto.setCreatedAt(post.getCreatedAt());
        return dto;
    }
}