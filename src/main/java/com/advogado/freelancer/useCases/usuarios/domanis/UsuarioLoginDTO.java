package com.advogado.freelancer.useCases.usuarios.domanis;
import lombok.Data;

@Data
public class UsuarioLoginDTO {
    private String email;
    private String senha;
}
