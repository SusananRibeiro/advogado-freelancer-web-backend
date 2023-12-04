package com.advogado.freelancer.controllers;
import com.advogado.freelancer.frameWork.annotions.LogRest;
import com.advogado.freelancer.frameWork.utils.ResponseUtil;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.audiencia.AudienciaService;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaRequestDom;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaResponseDom;
import com.advogado.freelancer.useCases.audiencia.impl.repositorys.AudienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/audiencia")
public class AudienciaController {
    @Autowired
    private AudienciaService audienciaService;

    @Autowired
    private AudienciaRepository audienciaRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/carregue")
    @LogRest
    public ResponseEntity<List<AudienciaResponseDom>> carregarAudiencia(){
        return ResponseEntity.ok(audienciaService.carregarAudiencia());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/carregue/{id}")
    @LogRest
    public ResponseEntity<AudienciaResponseDom> carregarAudienciaById(@PathVariable Long id) throws SenacException{
        return ResponseEntity.ok(audienciaService.carregarAudienciaById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/crie")
    @LogRest
    public ResponseEntity<?> criarAudiencia(@RequestBody AudienciaRequestDom audienciaRequestDom) throws Exception{
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(audienciaService.criarAudiencia(audienciaRequestDom));
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
    @PutMapping("/atualize/{id}")
    @LogRest
    public ResponseEntity<?> atualizarAudiencia(@PathVariable  Long id,@RequestBody AudienciaRequestDom audienciaRequestDom){
        try {
            return ResponseEntity.ok(audienciaService.atualizarAudiencia(id, audienciaRequestDom));
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
    public ResponseEntity<Void> deletarAudiencia(@PathVariable Long id){
        audienciaService.deletarAudiencia(id);

        return ResponseEntity.ok(null);
    }
}
