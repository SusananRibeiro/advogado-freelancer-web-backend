package com.advogado.freelancer.useCases.usuarios.domanis;
import lombok.Data;

@Data
public class UsuarioResponseDom {
    private Long id;
    private String nomeCompleto;
    private String email;
    private String senha;
}
