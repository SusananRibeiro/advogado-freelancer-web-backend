package com.advogado.freelancer.useCases.clientes;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesRequestDom;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesResponseDom;
import java.util.List;

public interface ClientesBusiness {
    public List<ClientesResponseDom> carregarClientes();
    ClientesResponseDom criarCliente(ClientesRequestDom clientesRequestDom) throws Exception;
    ClientesResponseDom atualizarCliente(Long id, ClientesRequestDom clientesRequestDom) throws SenacException;
    void deletarCliente(Long id);
    ClientesResponseDom carregarClienteById(Long id) throws SenacException;
}
