package com.advogado.freelancer.controllers;

import com.advogado.freelancer.frameWork.DataReponse;
import com.advogado.freelancer.frameWork.InfoRow;
import com.advogado.freelancer.frameWork.annotions.LogRest;
import com.advogado.freelancer.frameWork.utils.ResponseUtil;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesResponseDom;
import com.advogado.freelancer.useCases.processos.domains.ProcessoRequestDom;
import com.advogado.freelancer.useCases.processos.domains.ProcessoResponseDom;
import com.advogado.freelancer.useCases.processos.impl.ProcessoServiceImpl;
import com.advogado.freelancer.useCases.processos.impl.mappers.ProcessoMapper;
import com.advogado.freelancer.useCases.processos.impl.repositorys.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/processos")
public class ProcessoController {
    @Autowired
    private ProcessoServiceImpl processoServiceImpl;
    @Autowired
    private ProcessoRepository processoRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/carregar")
    @LogRest
    public ResponseEntity<List<ProcessoResponseDom>> carregarProcessos() {
        return ResponseEntity.ok(processoServiceImpl.carregarProcessos());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/carregar/{id}")
    @LogRest
    public ResponseEntity<?> carregarProcessoById(@PathVariable Long id) throws SenacException {

        try {
            return ResponseEntity.ok(processoServiceImpl.carregarProcessoById(id));
        } catch (SenacException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/criar")
    @LogRest
    public ResponseEntity<?> criarProcesso
            (@RequestBody ProcessoRequestDom processoRequestDom) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(processoServiceImpl.criarProcesso(processoRequestDom));
        } catch (SenacException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/atualizar/{id}")
    @LogRest
    public ResponseEntity<?> atualizarProcesso
            (@PathVariable Long id,
             @RequestBody ProcessoRequestDom processoRequestDom) {
        try {
            return ResponseEntity.ok(
                    processoServiceImpl.atualizarProcesso(id, processoRequestDom));
        } catch (SenacException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deletar/{id}")
    @LogRest
    public ResponseEntity<?> deletarProcesso(@PathVariable Long id) {

        try {
            processoServiceImpl.deletarProcesso(id);
            return ResponseEntity.ok(Map.of("message", "Processo removido!"));
        } catch (SenacException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMapper(e.getMessages()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.responseMapper("Erro não mapeado: " + e.getMessage()));
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/carregar/usuarioId/{usuarioId}")
    @LogRest
    public ResponseEntity<List<ProcessoResponseDom>> carregarProcessoByUsuarioId(@PathVariable Long usuarioId) throws SenacException {
        return ResponseEntity.ok(processoServiceImpl.carregarProcessoByUsuarioId(usuarioId));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/carregar/page")
    public ResponseEntity<DataReponse> carregarProcessoPorPagina(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);

        var processoList = processoRepository.findAll(paging);

        List<ProcessoResponseDom> processoResponseList = processoList.getContent()
                .stream()
                .map(ProcessoMapper::processosToProcessoResponseDom)
                .collect(Collectors.toList());

        InfoRow infoRow = new InfoRow();
        // número da pagina atual
        infoRow.setPage(processoList.getNumber() + 1);
        // total de paginas a serem listadas
        infoRow.setPageCount(processoList.getTotalPages());
        // total de elementos
        infoRow.setTotalElements(processoList.getTotalElements());

        DataReponse out = new DataReponse();
        out.setInfoRows(infoRow);
        out.setRows(processoList);

        return ResponseEntity.ok(out);
    }
}
