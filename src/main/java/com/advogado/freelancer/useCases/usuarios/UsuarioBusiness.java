package com.advogado.freelancer.useCases.usuarios;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioRequestDom;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioResponseDom;
import java.util.List;

public interface UsuarioBusiness {
    public List<UsuarioResponseDom> carregarUsuario();
    UsuarioResponseDom criarUsuario(UsuarioRequestDom usuarioRequestDom) throws Exception;
    UsuarioResponseDom atualizarUsuario(Long id, UsuarioRequestDom usuarioRequestDom) throws SenacException;
    void deletarUsuario(Long id);

    UsuarioResponseDom carregarUsuariosById(Long id) throws SenacException;

    boolean carregarUsuarioByIdEmailSenha(Long id, String email, String senha) throws SenacException;
}
