package com.advogado.freelancer.useCases.clientes.impl;
import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.frameWork.utils.EstadosDoBrasil;
import com.advogado.freelancer.frameWork.annotions.Business;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.frameWork.utils.StatusAtivoInativo;
import com.advogado.freelancer.frameWork.utils.StringUtil;
import com.advogado.freelancer.useCases.clientes.ClientesBusiness;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesRequestDom;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesResponseDom;
import com.advogado.freelancer.useCases.clientes.impl.mappers.ClientesMapper;
import com.advogado.freelancer.useCases.clientes.impl.repositorys.ClienteRelatorioRepository;
import com.advogado.freelancer.useCases.clientes.impl.repositorys.ClienteUsuarioRepository;
import com.advogado.freelancer.useCases.clientes.impl.repositorys.ClientesRespository;
import com.advogado.freelancer.useCases.usuarios.impl.repositorys.UsuarioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class ClientesBusinessImpl implements ClientesBusiness {
    @Autowired
    private ClientesRespository clientesRepository;
    @Autowired
    private ClienteRelatorioRepository clienteRelatorioRepository;
    @Autowired
    private ClienteUsuarioRepository clienteUsuarioRepository;

    @Autowired
    private UsuarioClienteRepository usuarioClienteRepository;
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
        Optional<Usuario> usuario = clienteUsuarioRepository.findById(clientesRequestDom.getUsuarioId());

        if(!usuario.isPresent()){
            throw new SenacException("Usuario não encontrado");
        }
        if (validarCPF(clientesRequestDom.getCpf())) {
            throw new SenacException("CPF já cadastrado para outro cliente!");
        }
        Clientes clienteRetorno = clientesRepository.save(ClientesMapper
                .clientesRequestDomToClientes(clientesRequestDom, usuario.get()));
        return ClientesMapper.clientesToClientesResponseDom(clienteRetorno);
    }

    @Override
    public ClientesResponseDom atualizarCliente(Long id, ClientesRequestDom clientesRequestDom) throws SenacException {
        Optional<Clientes> clienteExistente = clientesRepository.findById(id);

        if (clienteExistente.isPresent()) {
            Clientes cliente = clienteExistente.get();
            // Implemente suas próprias validações aqui, se necessário
            validacaoManutencaoCliente(clientesRequestDom);

            // Verificar se o CPF do cliente existente corresponde ao CPF informado na requisição
            if (!cliente.getCpf().equals(clientesRequestDom.getCpf())) {
                // CPF informado é diferente do CPF armazenado para este cliente, então valida o CPF existente
                if (validarCPF(clientesRequestDom.getCpf())) {
                    throw new SenacException("CPF já cadastrado para outro cliente!");
                }
            }
            // Impedir a alteração do CPF
            if (!cliente.getCpf().equals(clientesRequestDom.getCpf())) {
                throw new SenacException("Não é permitido alterar o CPF do cliente!");
            }
            // Atualizar os dados do cliente (exceto CPF)
            cliente.setNomeCompleto(clientesRequestDom.getNomeCompleto());
            cliente.setRua(clientesRequestDom.getRua());
            cliente.setNumero(clientesRequestDom.getNumero());
            cliente.setBairro(clientesRequestDom.getBairro());
            cliente.setCidade(clientesRequestDom.getCidade());
            cliente.setUf(clientesRequestDom.getUf());
            cliente.setCep(clientesRequestDom.getCep());
            cliente.setPais(clientesRequestDom.getPais());
            cliente.setTelefone(clientesRequestDom.getTelefone());
            cliente.setEmail(clientesRequestDom.getEmail());
            cliente.setComplemento(clientesRequestDom.getComplemento());
            cliente.setStatus(clientesRequestDom.getStatus());

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

    @Override
    public List<ClientesResponseDom> carregarClientesByUsuarioId(Long id) throws SenacException {

        List<Clientes> optionalCliente = usuarioClienteRepository.carregarClientesByUsuarioId(id);

        List<ClientesResponseDom> out = optionalCliente
                .stream()
                .map(ClientesMapper:: clientesToClientesResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    private void validacaoManutencaoCliente(ClientesRequestDom cliente) throws SenacException {
        if(StringUtil.validarString(cliente.getNomeCompleto())){
            throw new SenacException("O nome do cliente é obrigatório.");
        }
        if(StringUtil.validarString(cliente.getCpf()) || !cliente.getCpf().matches("\\d{11}")){
            throw new SenacException("O CPF do cliente é obrigatório.");
        }

        if (cliente.getDataNascimento() == null) {
            throw new SenacException("A data de nascimento é obrigatório.");
        }
        if(StringUtil.validarString(cliente.getRua())){
            throw new SenacException("A rua é obrigatório.");
        }
        if(StringUtil.validarString(cliente.getBairro())){
            throw new SenacException("O bairro é obrigatório.");
        }
        if(StringUtil.validarString(cliente.getBairro())){
            throw new SenacException("A cidade é obrigatório.");
        }
        if(StringUtil.validarString(String.valueOf(cliente.getUf()))){
            throw new SenacException("A sigla do estado é obrigatório.");
        }
        if(verificarUF(String.valueOf(cliente.getUf())) == false){
            throw new SenacException("Estado inválido!");
        }
        if(StringUtil.validarString(String.valueOf(cliente.getCep()))){
            throw new SenacException("O CEP é obrigatório.");
        }
        if(StringUtil.validarString(cliente.getPais())){
            throw new SenacException("O país é obrigatório.");
        }
        if(StringUtil.validarString(cliente.getTelefone()) || !cliente.getTelefone().matches("\\d{11}")){
            throw new SenacException("O telefone é obrigatório.");
        }
        if(StringUtil.validarString(String.valueOf(cliente.getStatus()))){
          throw new SenacException("O status é obrigatório.");
        }
        if(verificarStatus(String.valueOf(cliente.getStatus())) == false){
            throw new SenacException("Status inválido!");
        }
    }

    // Validação do CPF
    public boolean validarCPF(String cpf) {
        return clienteRelatorioRepository.existsByCpf(cpf);
    }

    // Validação estados
    public boolean verificarUF(String uf) {
        EstadosDoBrasil estadosDoBrasil = EstadosDoBrasil.valueOf(uf);
        try {
            estadosDoBrasil.valueOf(uf);
            return true; // O estado é válido
        } catch (IllegalArgumentException e) {
            return false; // O estado é inválido
        }
    }

    // Validação do Status
    public boolean verificarStatus(String status) {
        StatusAtivoInativo statusAtivoInativo = StatusAtivoInativo.valueOf(status);
        try {
            statusAtivoInativo.valueOf(status);
            return true; // O estado é válido
        } catch (IllegalArgumentException e) {
            return false; // O estado é inválido
        }
    }

}
