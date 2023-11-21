package com.advogado.freelancer.useCases.clientes.impl.mappers;
import com.advogado.freelancer.entities.Cliente;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesRequestDom;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesResponseDom;

public class ClientesMapper {
    public static ClientesResponseDom clientesToClientesResponseDom(Cliente clientes){
        ClientesResponseDom out = new ClientesResponseDom();
        out.setId(clientes.getId());
        out.setNomeCompleto(clientes.getNomeCompleto());
        out.setCpfOuCnpj(clientes.getCpfOuCnpj());
        out.setDataNascimento(clientes.getDataNascimento());
        out.setRua(clientes.getRua());
        out.setNumero(clientes.getNumero());
        out.setBairro(clientes.getBairro());
        out.setUf(clientes.getUf());
        out.setCep(clientes.getCep());
        out.setPais(clientes.getPais());
        out.setTelefone(clientes.getTelefone());
        out.setEmail(clientes.getEmail());
        out.setComplemento(clientes.getComplemento());
        out.setStatus(clientes.isStatus());
        return out;
    }

    public static Cliente clientesRequestDomToClientes(ClientesRequestDom clientesRequestDom){
        Cliente out = new Cliente();
        out.setNomeCompleto(clientesRequestDom.getNomeCompleto());
        out.setCpfOuCnpj(clientesRequestDom.getCpfOuCnpj());
        out.setDataNascimento(clientesRequestDom.getDataNascimento());
        out.setRua(clientesRequestDom.getRua());
        out.setNumero(clientesRequestDom.getNumero());
        out.setBairro(clientesRequestDom.getBairro());
        out.setUf(clientesRequestDom.getUf());
        out.setCep(clientesRequestDom.getCep());
        out.setPais(clientesRequestDom.getPais());
        out.setTelefone(clientesRequestDom.getTelefone());
        out.setEmail(clientesRequestDom.getEmail());
        out.setComplemento(clientesRequestDom.getComplemento());
        out.setStatus(clientesRequestDom.isStatus());
        return out;
    }

}
