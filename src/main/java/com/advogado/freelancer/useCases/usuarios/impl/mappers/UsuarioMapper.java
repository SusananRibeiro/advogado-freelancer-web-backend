package com.advogado.freelancer.useCases.usuarios.impl.mappers;
import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioRequestDom;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioResponseDom;

public class UsuarioMapper {

    public static UsuarioResponseDom usuariosToUsuariosResponseDom(Usuario usuario){
        UsuarioResponseDom out = new UsuarioResponseDom();
        out.setId(usuario.getId());
        out.setNomeCompleto(usuario.getNomeCompleto());
        out.setEmail(usuario.getEmail());
        out.setSenha(usuario.getSenha());
        return out;
    }

    // Método para converter UsuarioRequestDom para Usuario
    public static Usuario usuarioRequestDomToUsuario(UsuarioRequestDom usuarioRequestDom){
        Usuario out = new Usuario();
        out.setNomeCompleto(usuarioRequestDom.getNomeCompleto());
        out.setEmail(usuarioRequestDom.getEmail());
        out.setSenha(usuarioRequestDom.getSenha());
        return out;
    }
}
