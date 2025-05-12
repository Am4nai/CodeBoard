package com.codeboard.codeboard_backend;

import com.codeboard.codeboard_backend.dto.request.PostCreateDto;
import com.codeboard.codeboard_backend.dto.request.UserCreateDto;
import com.codeboard.codeboard_backend.dto.response.AuthResponseDto;
import com.codeboard.codeboard_backend.model.Post;
import com.codeboard.codeboard_backend.model.User;
import com.codeboard.codeboard_backend.model.enums.UserRoleEnum;
import com.codeboard.codeboard_backend.repository.PostRepository;
import com.codeboard.codeboard_backend.repository.UserRepository;
import com.codeboard.codeboard_backend.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=test")
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testAddUserWithDto() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setUsername("test_user");
        userCreateDto.setEmail("test@example.com");
        userCreateDto.setPassword("password123");

        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());
        user.setPasswordHash(hashPassword(userCreateDto.getPassword()));
        user.setUserRoleEnum(UserRoleEnum.USER);

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUsername("test_user");

        assertTrue(foundUser.isPresent(), "Пользователь должен быть найден");

        assertEquals("test_user", foundUser.get().getUsername(), "Имя пользователя должно совпадать");
        assertEquals("test@example.com", foundUser.get().getEmail(), "Email должен совпадать");
        assertEquals(UserRoleEnum.USER, foundUser.get().getUserRoleEnum(), "Роль должна быть USER");
    }

    @Test
    void testCreatePost() {
        User user = new User();
        user.setUsername("post_test_user");
        user.setEmail("post_test@example.com");
        user.setPasswordHash(hashPassword("password123"));
        user.setUserRoleEnum(UserRoleEnum.USER);
        userRepository.save(user);

        PostCreateDto postCreateDto = new PostCreateDto();
        postCreateDto.setTitle("Test Post");
        postCreateDto.setContent("This is a test post content.");
        postCreateDto.setLanguageName("Java");
        postCreateDto.setVisibility("PUBLIC");
        postCreateDto.setAuthorUsername(user.getUsername());

        Post post = new Post();
        post.setTitle(postCreateDto.getTitle());
        post.setContent(postCreateDto.getContent());
        post.setLanguageName(postCreateDto.getLanguageName());
        post.setVisibility(postCreateDto.getVisibility());
        post.setAuthorUsername(postCreateDto.getAuthorUsername());

        postRepository.save(post);

        Optional<Post> foundPost = postRepository.findByTitle("Test Post");

        assertTrue(foundPost.isPresent(), "Пост должен быть найден");

        assertEquals("Test Post", foundPost.get().getTitle(), "Заголовок поста должен совпадать");
        assertEquals("This is a test post content.", foundPost.get().getContent(), "Содержимое поста должно совпадать");
        assertEquals("Java", foundPost.get().getLanguageName(), "Язык программирования должен совпадать");
        assertEquals("PUBLIC", foundPost.get().getVisibility(), "Видимость поста должна быть PUBLIC");
        assertEquals("post_test_user", foundPost.get().getAuthorUsername(), "Имя автора должно совпадать");
    }

    @Test
    void testDeletePost() {
        User user = new User();
        user.setUsername("delete_post_user");
        user.setEmail("delete_post@example.com");
        user.setPasswordHash(hashPassword("password123"));
        user.setUserRoleEnum(UserRoleEnum.USER);
        userRepository.save(user);

        // Создание тестового поста
        Post post = new Post();
        post.setTitle("To Be Deleted");
        post.setContent("This post will be deleted.");
        post.setLanguageName("Python");
        post.setVisibility("PRIVATE");
        post.setAuthorUsername(user.getUsername());
        Post savedPost = postRepository.save(post);

        // Удаление поста
        postRepository.deleteById(savedPost.getId());

        Optional<Post> deletedPost = postRepository.findById(savedPost.getId());

        assertFalse(deletedPost.isPresent(), "Пост должен быть удален");
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setUsername("to_be_deleted");
        user.setEmail("delete@example.com");
        user.setPasswordHash(hashPassword("password123"));
        user.setUserRoleEnum(UserRoleEnum.USER);

        User savedUser = userRepository.save(user);

        userRepository.deleteById(savedUser.getId());

        Optional<User> deletedUser = userRepository.findById(savedUser.getId());

        assertFalse(deletedUser.isPresent(), "Пользователь должен быть удален");
    }

    @Test
    void testUserAuthentication() {
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setUsername("auth_test_user");
        userCreateDto.setEmail("auth_test@example.com");
        userCreateDto.setPassword("password123");

        authService.registerUser(userCreateDto);

        AuthResponseDto authResponse = authService.authenticateUser("auth_test_user", "password123");

        assertNotNull(authResponse.getToken(), "Токен должен быть сгенерирован");

        assertEquals("auth_test_user", authResponse.getUser().getUsername(), "Имя пользователя должно совпадать");
        assertEquals("auth_test@example.com", authResponse.getUser().getEmail(), "Email должен совпадать");
    }

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
