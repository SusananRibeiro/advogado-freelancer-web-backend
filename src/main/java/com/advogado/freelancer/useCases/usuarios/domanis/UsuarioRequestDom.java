package com.advogado.freelancer.useCases.usuarios.domanis;
import lombok.Data;

@Data
public class UsuarioRequestDom {

    private Long id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String confirmaSenha;

}
