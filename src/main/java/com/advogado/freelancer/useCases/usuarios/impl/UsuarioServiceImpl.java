package com.advogado.freelancer.useCases.usuarios.impl;
import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.useCases.usuarios.UsuarioService;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioRequestDom;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioBusinessImpl usuarioBusiness;

    @Override
    public UsuarioResponseDom criarUsuario(UsuarioRequestDom usuarioRequestDom) throws Exception {
        return usuarioBusiness.criarUsuario(usuarioRequestDom);
    }

    // TESTE Login
    @Override
    public Usuario fazerLogin(String email, String senha) {
        return usuarioBusiness.fazerLogin(email, senha);
    }



}
