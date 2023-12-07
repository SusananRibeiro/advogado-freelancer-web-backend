package com.advogado.freelancer.useCases.usuarios.domanis;
import lombok.Data;
import java.util.List;

@Data
public class UsuarioResponseDom {
    private Long id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String confirmaSenha;
    private List<UsuarioClienteResponseDom> clientesList;
}
