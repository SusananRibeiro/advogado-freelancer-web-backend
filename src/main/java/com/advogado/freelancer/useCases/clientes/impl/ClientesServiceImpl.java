package com.advogado.freelancer.useCases.clientes.impl;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.clientes.ClientesService;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesRequestDom;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ClientesServiceImpl implements ClientesService {
    @Autowired
    private ClientesBusinessImpl clientesBusinessImpl;
    @Override
    public List<ClientesResponseDom> carregarClientes() {
        return clientesBusinessImpl.carregarClientes();
    }

    @Override
    public ClientesResponseDom criarCliente(ClientesRequestDom clientesRequestDom) throws Exception {
        return clientesBusinessImpl.criarCliente(clientesRequestDom);
    }

    @Override
    public ClientesResponseDom atualizarCliente(Long id, ClientesRequestDom clientesRequestDom) throws SenacException {
        return clientesBusinessImpl.atualizarCliente(id, clientesRequestDom);
    }

    @Override
    public void deletarCliente(Long id) {
        clientesBusinessImpl.deletarCliente(id);
    }

    @Override
    public ClientesResponseDom carregarClienteById(Long id) throws SenacException {
        return clientesBusinessImpl.carregarClienteById(id);
    }

    @Override
    public List<ClientesResponseDom> carregarClientesByUsuarioId(Long id) throws SenacException{
        return clientesBusinessImpl.carregarClientesByUsuarioId(id);
    }
}
