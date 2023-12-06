package com.advogado.freelancer.controllers;
import com.advogado.freelancer.frameWork.annotions.LogRest;
import com.advogado.freelancer.frameWork.utils.ResponseUtil;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioRequestDom;
import com.advogado.freelancer.useCases.usuarios.impl.UsuarioServiceImpl;
import com.advogado.freelancer.useCases.usuarios.impl.repositorys.UsuarioRelatorioRepository;
import com.advogado.freelancer.useCases.usuarios.impl.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRelatorioRepository usuarioRelatorioRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/crie")
    @LogRest
    public ResponseEntity<?> criarUsuario
            (@RequestBody UsuarioRequestDom usuarioRequestDom){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(usuarioRequestDom));
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

}
