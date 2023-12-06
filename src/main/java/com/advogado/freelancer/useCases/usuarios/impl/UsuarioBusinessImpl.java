package com.advogado.freelancer.useCases.usuarios.impl;
import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.frameWork.annotions.Business;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.frameWork.utils.StringUtil;
import com.advogado.freelancer.useCases.usuarios.UsuarioBusiness;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioRequestDom;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioResponseDom;
import com.advogado.freelancer.useCases.usuarios.impl.mappers.UsuarioMapper;
import com.advogado.freelancer.useCases.usuarios.impl.repositorys.UsuarioClienteRepository;
import com.advogado.freelancer.useCases.usuarios.impl.repositorys.UsuarioRelatorioRepository;
import com.advogado.freelancer.useCases.usuarios.impl.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class UsuarioBusinessImpl implements UsuarioBusiness {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioRelatorioRepository usuarioRelatorioRepository;
    @Autowired
    private UsuarioClienteRepository usuarioClienteRepository;

    @Override
    public UsuarioResponseDom carregarUsuarioById(Long id) throws SenacException {
        Usuario usuario = usuarioRepository.findById(id).get();

//        List<Clientes> clientes = usuarioClienteRepository.findByIdAndClientesUsuarioId(id);

        List<Clientes> clientes = usuarioClienteRepository.carregarClientesByUsuarioId(id);

        UsuarioResponseDom out = UsuarioMapper.usuariosToUsuariosResponseDom(usuario, clientes);

        return out;
    }


    @Override
    public List<UsuarioResponseDom> carregarUsuarios() {
        List<Usuario> usuarioList = usuarioRepository.findAll();

        List<UsuarioResponseDom> out = usuarioList.stream()
                .map(UsuarioMapper::usuariosToUsuariosResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public UsuarioResponseDom criarUsuario(UsuarioRequestDom usuarioRequestDom) throws Exception {
        List<String> messages = this.validacaoUsuario(usuarioRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }
        Usuario usuario = UsuarioMapper.usuarioRequestDomToUsuario(usuarioRequestDom);
        Usuario resultUsuario = usuarioRepository.save(usuario);
        UsuarioResponseDom out = UsuarioMapper.usuariosToUsuariosResponseDom(resultUsuario);
        return out;
    }



    // TESTE Login
    @Override
    public Usuario fazerLogin(String email, String senha) {
        return usuarioRelatorioRepository.findByEmailAndSenha(email, senha);
    }


// Validações
    public boolean verificarExistenciaEmail(String email) {
        return usuarioRelatorioRepository.existsByEmail(email);
    }

    private List<String> validacaoUsuario(UsuarioRequestDom usuarioRequestDom){
        List<String> messages = new ArrayList<>();

        if(StringUtil.validarString(usuarioRequestDom.getNomeCompleto())){
            messages.add("Não foi informado o nome e sobrenome!");
        }

        if(StringUtil.validarString(usuarioRequestDom.getEmail())){
            messages.add("Não foi informado e-mail!");
        }
        if(verificarExistenciaEmail(usuarioRequestDom.getEmail()) == true) {
            messages.add("E-mail já cadastrado!");
        }

        if(StringUtil.validarString(usuarioRequestDom.getSenha()) ||
                !usuarioRequestDom.getSenha().matches(".{8}")){
            messages.add("Senha inválida, deve conter exatamente 8 dígitos!");
        }
        if(!validarSenha(usuarioRequestDom.getSenha(), usuarioRequestDom.getConfirmaSenha()) == true) {
            messages.add("Senha de confirmação inválida!");
        }
        return messages;
    }

    public boolean validarSenha(String senha, String senhaConfirmacao) {
        return senha.equals(senhaConfirmacao);
    }

}
