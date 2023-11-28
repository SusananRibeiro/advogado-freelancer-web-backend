package com.advogado.freelancer.useCases.usuarios.impl;
import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.frameWork.annotions.Business;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.frameWork.utils.StringUtil;
import com.advogado.freelancer.useCases.usuarios.UsuarioBusiness;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioRequestDom;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioResponseDom;
import com.advogado.freelancer.useCases.usuarios.impl.mappers.UsuarioMapper;
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

    @Override
    public List<UsuarioResponseDom> carregarUsuario() {
        List<Usuario> usuarioList = usuarioRepository.findAll();

        List<UsuarioResponseDom> out = usuarioList
                .stream()
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

    @Override
    public UsuarioResponseDom atualizarUsuario(Long id, UsuarioRequestDom usuarioRequestDom) throws SenacException {
        List<String> messages = this.validacaoUsuario(usuarioRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Usuario> usuario = usuarioRepository.findById(id).map(record -> {
            record.setNomeCompleto(usuarioRequestDom.getNomeCompleto());
            record.setEmail(usuarioRequestDom.getEmail());
            record.setSenha(usuarioRequestDom.getSenha());

            return usuarioRepository.save(record);
        });

        if(!usuario.isPresent()){
            throw new SenacException("Usuário informando não existe!");
        }

        UsuarioResponseDom out = UsuarioMapper.usuariosToUsuariosResponseDom(usuario.get());

        return out;
    }

    @Override
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDom carregarUsuarioById(Long id) throws SenacException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if(!optionalUsuario.isPresent()) {
            throw new SenacException("Usuário não encontrado");
        }
        Usuario usuario = optionalUsuario.get();
        UsuarioResponseDom out = UsuarioMapper.usuariosToUsuariosResponseDom(usuario);
        return out;
    }


    private List<String> validacaoUsuario(UsuarioRequestDom usuarioRequestDom){
        List<String> messages = new ArrayList<>();

        if(StringUtil.validarString(usuarioRequestDom.getNomeCompleto())){
            messages.add("Não foi informado o nome e sobrenome!");
            return messages;
        }

        if(StringUtil.validarString(usuarioRequestDom.getEmail())){
            messages.add("Não foi informado e-mail!");
            return messages;
        }

        if(StringUtil.validarString(usuarioRequestDom.getSenha())){
            messages.add("Não foi informado a senha!");
            return messages;
        }
        return messages;
    }

}
