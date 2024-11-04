package com.unicarioca.portal.controller.dto;

import com.unicarioca.portal.util.TipoUsuario;
import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String senha;
    private String tipoUsuario;
    

}
