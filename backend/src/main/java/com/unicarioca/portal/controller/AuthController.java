package com.unicarioca.portal.controller;

import com.unicarioca.portal.controller.dto.LoginRequest;
import com.unicarioca.portal.controller.dto.LoginResponse;
import com.unicarioca.portal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        try {
            authService.login(loginRequest);
            return ResponseEntity.ok(new LoginResponse("200", "Login efetuado com sucesso"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(new LoginResponse("403", "Email ou senha inválidos"));
        }
    }
}
