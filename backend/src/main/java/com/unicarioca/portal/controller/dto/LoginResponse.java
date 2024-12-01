package com.unicarioca.portal.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String codigo;
    private String token;
    private int expiraEm;
}
