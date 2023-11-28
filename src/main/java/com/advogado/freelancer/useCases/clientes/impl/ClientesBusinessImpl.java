package com.advogado.freelancer.useCases.clientes.impl;
import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.frameWork.ConversorData;
import com.advogado.freelancer.frameWork.annotions.Business;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.frameWork.utils.StringUtil;
import com.advogado.freelancer.useCases.clientes.ClientesBusiness;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesRequestDom;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesResponseDom;
import com.advogado.freelancer.useCases.clientes.impl.mappers.ClientesMapper;
import com.advogado.freelancer.useCases.clientes.impl.repositorys.ClientesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            record.setDataNascimento(ConversorData.converterDataBrasileiraParaDataAmericana(clientesRequestDom.getDataNascimento()));
            record.setRua(clientesRequestDom.getRua());
            record.setNumero(clientesRequestDom.getNumero());
            record.setBairro(clientesRequestDom.getBairro());
            record.setCidade(clientesRequestDom.getCidade());
            record.setUf(clientesRequestDom.getUf());
            record.setCep(clientesRequestDom.getCep());
            record.setPais(clientesRequestDom.getPais());
            record.setTelefone(clientesRequestDom.getTelefone());
            record.setEmail(clientesRequestDom.getEmail());
            record.setComplemento(clientesRequestDom.getComplemento());
            record.setStatus(clientesRequestDom.getStatus());

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
            messages.add("Não foi informado o nome e sobrenome do cliente!");
        }
        if(StringUtil.validarString(cliente.getCpfOuCnpj())){
            messages.add("Não foi informado o CPF/CNPJ do cliente!");
        }

        if (cliente.getDataNascimento() == null) {
            messages.add("Não foi informada a data de nascimento do cliente!");
        }

        if(StringUtil.validarString(cliente.getRua())){
            messages.add("Não foi informado a rua do endereço do cliente!");
        }

        if(StringUtil.validarString(cliente.getBairro())){
            messages.add("Não foi informado o bairro do endereço do cliente!");
        }

        if(StringUtil.validarString(cliente.getBairro())){
            messages.add("Não foi informado a cidade do endereço do cliente!");
        }

        if(StringUtil.validarString(cliente.getUf())){
            messages.add("Não foi informado a UF do endereço do cliente!");
        }
        if(StringUtil.validarString(String.valueOf(cliente.getCep()))){
            messages.add("Não foi informado o CEP do endereço do cliente!");
        }
        if(StringUtil.validarString(cliente.getPais())){
            messages.add("Não foi informado o país do endereço do cliente!");
        }
        if(StringUtil.validarString(cliente.getTelefone())){
            messages.add("Não foi informado o telefone do cliente!");
        }
//      if(StringUtil.validarString(cliente.getStatus())){
//            messages.add("Não foi informado o status do cliente!");
//      }
        return messages;
    }

}
