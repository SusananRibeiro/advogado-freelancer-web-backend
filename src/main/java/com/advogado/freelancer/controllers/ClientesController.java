package com.advogado.freelancer.controllers;
import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.frameWork.DataReponse;
import com.advogado.freelancer.frameWork.InfoRow;
import com.advogado.freelancer.frameWork.annotions.LogRest;
import com.advogado.freelancer.frameWork.utils.ResponseUtil;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesRequestDom;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesResponseDom;
import com.advogado.freelancer.useCases.clientes.impl.ClientesServiceImpl;
import com.advogado.freelancer.useCases.clientes.impl.mappers.ClientesMapper;
import com.advogado.freelancer.useCases.clientes.impl.repositorys.ClientesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
    @Autowired
    private ClientesServiceImpl clientesService;

    @Autowired
    private ClientesRespository clientesRespository;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/carregue")
    @LogRest
    public ResponseEntity<List<ClientesResponseDom>> carregarClientes(){
        return ResponseEntity.ok(clientesService.carregarClientes());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/carregue/{id}")
    @LogRest
    public ResponseEntity<ClientesResponseDom> carregarClienteById(@PathVariable Long id) throws SenacException {
        return ResponseEntity.ok(clientesService.carregarClienteById(id));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/cadastro-cliente/crie")
    @LogRest
    public ResponseEntity<?> criarCliente
            (@RequestBody ClientesRequestDom clientesRequestDom){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.criarCliente(clientesRequestDom));
        } catch (SenacException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/cadastro-cliente/atualize/{id}")
    @LogRest
    public ResponseEntity<?> atualizarCliente
            (@PathVariable Long id,
             @RequestBody ClientesRequestDom clientesRequestDom){
        try {
            return ResponseEntity.ok(
                    clientesService.atualizarCliente(id, clientesRequestDom));
        } catch (SenacException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{id}")
    @LogRest
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clientesService.deletarCliente(id);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/carregue/pag")
    public ResponseEntity<DataReponse> carregarPedidos(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size){
        // Page é o número da página
        // Size é a quantidade de registros por página
        Pageable paging = PageRequest.of(page, size);

        Page<Clientes> pedidoList = clientesRespository.findAll(paging);

        List<ClientesResponseDom> pedidosResponseList = pedidoList.getContent()
                .stream()
                .map(ClientesMapper::clientesToClientesResponseDom)
                .collect(Collectors.toList());

        InfoRow infoRow = new InfoRow();
        // numero da pagina atual
        infoRow.setPage(pedidoList.getNumber() + 1);
        // total de paginas a serem listadas
        infoRow.setPageCount(pedidoList.getTotalPages());
        // total de elementos
        infoRow.setTotalElements(pedidoList.getTotalElements());

        DataReponse out = new DataReponse();
        out.setInfoRows(infoRow);
        out.setRows(pedidosResponseList);

        return ResponseEntity.ok(out);
    }

}
