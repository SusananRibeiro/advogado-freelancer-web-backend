package com.advogado.freelancer.useCases.usuarios.impl;
import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.usuarios.UsuarioService;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioRequestDom;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioBusinessImpl usuarioBusiness;

    @Override
    public List<UsuarioResponseDom> carregarUsuarios() {
        return usuarioBusiness.carregarUsuarios();
    }

    @Override
    public UsuarioResponseDom criarUsuario(UsuarioRequestDom usuarioRequestDom) throws Exception {
        return usuarioBusiness.criarUsuario(usuarioRequestDom);
    }

    // TESTE Login
    @Override
    public Usuario fazerLogin(String email, String senha) {
        return usuarioBusiness.fazerLogin(email, senha);
    }

    @Override
    public UsuarioResponseDom carregarUsuarioById(Long id) throws SenacException {
        return usuarioBusiness.carregarUsuarioById(id);
    }


}
