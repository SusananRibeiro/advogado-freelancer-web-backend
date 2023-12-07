package com.advogado.freelancer.useCases.usuarios.impl.mappers;
import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioClienteResponseDom;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioRequestDom;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioResponseDom;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    // Request - Método para converter UsuarioRequestDom para Usuario
    public static Usuario usuarioRequestDomToUsuario(UsuarioRequestDom usuarioRequestDom){
        Usuario out = new Usuario();
        out.setNomeCompleto(usuarioRequestDom.getNomeCompleto());
        out.setEmail(usuarioRequestDom.getEmail());
        out.setSenha(usuarioRequestDom.getSenha());
        out.setConfirmaSenha(usuarioRequestDom.getConfirmaSenha());
        return out;
    }

    // Response
    public static UsuarioResponseDom usuariosToUsuariosResponseDom(Usuario usuario){
        UsuarioResponseDom out = new UsuarioResponseDom();
        out.setId(usuario.getId());
        out.setNomeCompleto(usuario.getNomeCompleto());
        out.setEmail(usuario.getEmail());
        out.setSenha(usuario.getSenha());
        out.setConfirmaSenha(usuario.getConfirmaSenha());
        return out;
    }


    public static UsuarioResponseDom usuariosToUsuariosResponseDom(Usuario usuario, List<Clientes> clientesList){
        UsuarioResponseDom out = UsuarioMapper.usuariosToUsuariosResponseDom(usuario);
        List<UsuarioClienteResponseDom> clienteResponseDomList = clientesList.stream()
                .map(UsuarioMapper::clienteToUsuarioClientesResponseDom)
                .collect(Collectors.toList());

        out.setClientesList(clienteResponseDomList);

        return out;
    }


    public static UsuarioClienteResponseDom clienteToUsuarioClientesResponseDom(Clientes clientes){
        UsuarioClienteResponseDom out = new UsuarioClienteResponseDom();
        out.setId(clientes.getId());
        out.setNomeCompleto(clientes.getNomeCompleto());
        out.setCpfOuCnpj(clientes.getCpfOuCnpj());
        out.setDataNascimento(clientes.getDataNascimento());
        out.setRua(clientes.getRua());
        out.setNumero(clientes.getNumero());
        out.setBairro(clientes.getBairro());
        out.setCidade(clientes.getCidade());
        out.setUf(clientes.getUf());
        out.setCep(clientes.getCep());
        out.setPais(clientes.getPais());
        out.setTelefone(clientes.getTelefone());
        out.setEmail(clientes.getEmail());
        out.setComplemento(clientes.getComplemento());
        out.setStatus(clientes.getStatus());
        return out;
    }

}
