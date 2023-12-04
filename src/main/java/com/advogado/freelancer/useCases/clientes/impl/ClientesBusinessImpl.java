package com.advogado.freelancer.useCases.clientes.impl;
import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.frameWork.annotions.Business;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.frameWork.utils.StringUtil;
import com.advogado.freelancer.useCases.clientes.ClientesBusiness;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesRequestDom;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesResponseDom;
import com.advogado.freelancer.useCases.clientes.impl.mappers.ClientesMapper;
import com.advogado.freelancer.useCases.clientes.impl.repositorys.ClienteRelatorioRepository;
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

    @Autowired
    private ClienteRelatorioRepository clienteRelatorioRepository;
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
        this.validacaoManutencaoCliente(clientesRequestDom);
        Clientes clientes = ClientesMapper.clientesRequestDomToClientes(clientesRequestDom);
        if(validarCPF(clientes.getCpfOuCnpj()) == true) {
            throw new SenacException("CPF/CNPJ já cadastrado!");
        }
        Clientes resultClientes = clientesRepository.save(clientes);
        ClientesResponseDom out = ClientesMapper.clientesToClientesResponseDom(resultClientes);
        return out;
    }

    @Override
    public ClientesResponseDom atualizarCliente(Long id, ClientesRequestDom clientesRequestDom) throws SenacException {
        Optional<Clientes> clienteExistente = clientesRepository.findById(id);

        if (clienteExistente.isPresent()) {
            Clientes cliente = clienteExistente.get();
            // Implemente suas próprias validações aqui, se necessário
            validacaoManutencaoCliente(clientesRequestDom);

            // Verificar se o CPF do cliente existente corresponde ao CPF informado na requisição
            if (!cliente.getCpfOuCnpj().equals(clientesRequestDom.getCpfOuCnpj())) {
                // CPF informado é diferente do CPF armazenado para este cliente, então valida o CPF existente
                if (validarCPF(clientesRequestDom.getCpfOuCnpj())) {
                    throw new SenacException("CPF/CNPJ já cadastrado para outro cliente!");
                }
            }
            // Impedir a alteração do CPF
            if (!cliente.getCpfOuCnpj().equals(clientesRequestDom.getCpfOuCnpj())) {
                throw new SenacException("Não é permitido alterar o CPF/CNPJ do cliente!");
            }
            // Atualizar os dados do cliente (exceto CPF)
            cliente.setNomeCompleto(clientesRequestDom.getNomeCompleto());
            cliente.setRua(clientesRequestDom.getRua());
            cliente.setNumero(clientesRequestDom.getNumero());
            cliente.setBairro(clientesRequestDom.getBairro());
            cliente.setCidade(clientesRequestDom.getCidade());
            cliente.setUf(clientesRequestDom.getUf().toUpperCase());
            cliente.setCep(clientesRequestDom.getCep());
            cliente.setPais(clientesRequestDom.getPais());
            cliente.setTelefone(clientesRequestDom.getTelefone());
            cliente.setEmail(clientesRequestDom.getEmail());
            cliente.setComplemento(clientesRequestDom.getComplemento());
            cliente.setStatus(clientesRequestDom.isStatus());

            // Salvar as alterações no banco de dados
            Clientes clienteAtualizado = clientesRepository.save(cliente);

            // Criar e retornar a resposta de sucesso com os dados atualizados do cliente
            ClientesResponseDom out = ClientesMapper.clientesToClientesResponseDom(clienteAtualizado);
            return out;
        } else {
            throw new SenacException("Cliente informado não existe!");
        }
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

    private void validacaoManutencaoCliente(ClientesRequestDom cliente) throws SenacException {
        if(StringUtil.validarString(cliente.getNomeCompleto())){
            throw new SenacException("Não foi informado o nome e sobrenome do cliente!");
        }
        if(StringUtil.validarString(cliente.getCpfOuCnpj()) || !cliente.getCpfOuCnpj().matches("\\d{11}")){
            throw new SenacException("Não foi informado o CPF/CNPJ do cliente!");
        }

        if (cliente.getDataNascimento() == null) {
            throw new SenacException("Não foi informada a data de nascimento do cliente!");
        }
        if(StringUtil.validarString(cliente.getRua())){
            throw new SenacException("Não foi informado a rua do endereço do cliente!");
        }
        if(StringUtil.validarString(cliente.getBairro())){
            throw new SenacException("Não foi informado o bairro do endereço do cliente!");
        }
        if(StringUtil.validarString(cliente.getBairro())){
            throw new SenacException("Não foi informado a cidade do endereço do cliente!");
        }
        if(StringUtil.validarString(cliente.getUf())){
            throw new SenacException("Não foi informado a UF do endereço do cliente!");
        }
        if(StringUtil.validarString(String.valueOf(cliente.getCep()))){
            throw new SenacException("Não foi informado o CEP do endereço do cliente!");
        }
        if(StringUtil.validarString(cliente.getPais())){
            throw new SenacException("Não foi informado o país do endereço do cliente!");
        }
        if(StringUtil.validarString(cliente.getTelefone()) || !cliente.getTelefone().matches("\\d{11}")){
            throw new SenacException("Não foi informado o telefone do cliente!");
        }
        if(StringUtil.validarString(String.valueOf(cliente.isStatus()))){
          throw new SenacException("Não foi informado o status do cliente!");
        }
    }

    // Cria uma validação
    public boolean validarCPF(String cpfOuCnpj) {
        return clienteRelatorioRepository.existsByCpfOuCnpj(cpfOuCnpj);
    }

}
