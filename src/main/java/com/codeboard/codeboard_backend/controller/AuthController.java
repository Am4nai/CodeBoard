package com.codeboard.codeboard_backend.controller;

import com.codeboard.codeboard_backend.dto.request.TokenValidationRequest;
import com.codeboard.codeboard_backend.dto.request.UserLoginDto;
import com.codeboard.codeboard_backend.dto.response.AuthResponseDto;
import com.codeboard.codeboard_backend.service.AuthService;
import com.codeboard.codeboard_backend.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserLoginDto loginDto) {
        AuthResponseDto response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    private final JwtUtils jwtUtils;

    @PostMapping("/validate-token")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestBody TokenValidationRequest request) {
        String token = request.getToken();

        if (token == null || token.isEmpty()) {
            Map<String, Object> response = Map.of(
                    "valid", false,
                    "message", "Token is missing"
            );
            return ResponseEntity.badRequest().body(response);
        }

        boolean isValid = jwtUtils.validateToken(token);

        // Формирование ответа
        Map<String, Object> response = new HashMap<>();
        if (isValid) {
            response.put("valid", true);
            response.put("message", "Token is valid");
            return ResponseEntity.ok(response);
        } else {
            response.put("valid", false);
            response.put("message", "Token is expired or invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}