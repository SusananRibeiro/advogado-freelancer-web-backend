package com.advogado.freelancer.useCases.clientes.impl;

import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.frameWork.annotions.Business;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.frameWork.utils.StringUtil;
import com.advogado.freelancer.useCases.clientes.ClientesBusiness;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesRequestDom;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesResponseDom;
import com.advogado.freelancer.useCases.clientes.impl.mappers.ClientesMapper;
import com.advogado.freelancer.useCases.clientes.impl.repositorys.ClientesRespository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class ClientesBusinessImpl implements ClientesBusiness {
    @Autowired
    private ClientesRespository clientesRepository;
    @Override
    public List<ClientesResponseDom> carregarClientes() {
        List<Clientes> clientesList = clientesRepository.findAll();

        List<ClientesResponseDom> out = clientesList
                .stream()
                .map(ClientesMapper:: clientesToClientesResponseDom)
                .collect(Collectors.toList());

        return out;

    }

    @Override
    public ClientesResponseDom criarCliente(ClientesRequestDom clientesRequestDom) throws Exception {
        List<String> messages = this.validacaoManutencaoCliente(clientesRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Clientes clientes = ClientesMapper.clientesRequestDomToClientes(clientesRequestDom);

        Clientes resultClientes = clientesRepository.save(clientes);

        ClientesResponseDom out = ClientesMapper.clientesToClientesResponseDom(resultClientes);

        return out;
    }

    @Override
    public ClientesResponseDom atualizarCliente(Long id, ClientesRequestDom clientesRequestDom) throws SenacException {
        List<String> messages = this.validacaoManutencaoCliente(clientesRequestDom);

        if(!messages.isEmpty()){
            throw new SenacException(messages);
        }

        Optional<Clientes> clientes = clientesRepository.findById(id).map(record -> {
            record.setNomeCompleto(clientesRequestDom.getNomeCompleto());
            record.setCpfOuCnpj(clientesRequestDom.getCpfOuCnpj());
            record.setDataNascimento(clientesRequestDom.getDataNascimento());
            record.setRua(clientesRequestDom.getRua());
            record.setNumero(clientesRequestDom.getNumero());
            record.setBairro(clientesRequestDom.getBairro());
            record.setUf(clientesRequestDom.getUf());
            record.setCep(clientesRequestDom.getCep());
            record.setPais(clientesRequestDom.getPais());
            record.setTelefone(clientesRequestDom.getTelefone());
            record.setEmail(clientesRequestDom.getEmail());
            record.setComplemento(clientesRequestDom.getComplemento());
            record.setStatus(clientesRequestDom.isStatus());

            return clientesRepository.save(record);
        });

        if(!clientes.isPresent()){
            throw new SenacException("Cliente informando não existe!");
        }

        ClientesResponseDom out =
                ClientesMapper.clientesToClientesResponseDom(clientes.get());

        return out;
    }

    @Override
    public void deletarCliente(Long id) {
        clientesRepository.deleteById(id);
    }

    @Override
    public ClientesResponseDom carregarClienteById(Long id) throws SenacException {
        Optional<Clientes> optionalCliente = clientesRepository.findById(id);

        if(!optionalCliente.isPresent()) {
            throw new SenacException("Cliente não encontrado");
        }
        Clientes cliente = optionalCliente.get();

        ClientesResponseDom out = ClientesMapper.clientesToClientesResponseDom(cliente);

        return out;
    }


    private List<String> validacaoManutencaoCliente(ClientesRequestDom cliente){
        List<String> messages = new ArrayList<>();

        if(StringUtil.validarString(cliente.getNomeCompleto())){
            messages.add("Cliente informado não possui nome!");
        }

        if(StringUtil.validarString(cliente.getEmail())){
            messages.add("Cliente informado não possui email!");
        }

        return messages;
    }

}
