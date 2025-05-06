package com.codeboard.codeboard_backend;

import com.codeboard.codeboard_backend.model.User;
import com.codeboard.codeboard_backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CodeBoardBackendApplicationTests {

	@Autowired
	private UserRepository userRepository; // Внедрение репозитория

	@Test
	void contextLoads() {
		// Проверка загрузки контекста
	}

	@Test
	void testFindAll() {
		List<User> users = userRepository.findAll();
		assertNotNull(users, "Список пользователей не должен быть null");
		assertFalse(users.isEmpty(), "Список пользователей не должен быть пустым");
	}
}