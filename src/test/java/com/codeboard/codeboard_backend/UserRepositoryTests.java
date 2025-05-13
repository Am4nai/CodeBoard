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
}
