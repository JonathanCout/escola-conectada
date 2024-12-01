package com.unicarioca.portal.controller;

import com.unicarioca.portal.controller.dto.LoginRequest;
import com.unicarioca.portal.controller.dto.LoginResponse;
import com.unicarioca.portal.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthService authService;

    @PostMapping("")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        try {
            log.info("POST /login/request={}",loginRequest);
            authService.login(loginRequest);
            return ResponseEntity.ok(new LoginResponse("200", "Login efetuado com sucesso"));

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(new LoginResponse("403", "Email ou senha inválidos"));
        }
    }
}
