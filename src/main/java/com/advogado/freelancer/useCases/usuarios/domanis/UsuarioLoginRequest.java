package com.advogado.freelancer.useCases.usuarios.domanis;
import lombok.Data;

@Data
public class UsuarioLoginRequest {
    private String email;
    private String senha;
}
