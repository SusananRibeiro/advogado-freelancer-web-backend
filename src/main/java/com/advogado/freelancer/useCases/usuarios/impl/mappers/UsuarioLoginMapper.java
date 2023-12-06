package com.advogado.freelancer.useCases.usuarios.impl.mappers;
import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioLoginDTO;

public class UsuarioLoginMapper {

    // Request - Método para converter UsuarioRequestDom para Usuario
    public static Usuario usuarioLoginDtoToUsuario(UsuarioLoginDTO usuarioLoginDTO){
        Usuario out = new Usuario();
        out.setNomeCompleto(usuarioLoginDTO.getEmail());
        out.setEmail(usuarioLoginDTO.getSenha());
        return out;
    }

    // Response
    public static UsuarioLoginDTO usuariosToUsuariosLoginDto(Usuario usuario){
        UsuarioLoginDTO out = new UsuarioLoginDTO();
        out.setEmail(usuario.getEmail());
        out.setSenha(usuario.getSenha());
        return out;
    }
}
