package com.advogado.freelancer.controllers;
import com.advogado.freelancer.frameWork.annotions.LogRest;
import com.advogado.freelancer.frameWork.utils.ResponseUtil;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesResponseDom;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioRequestDom;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioResponseDom;
import com.advogado.freelancer.useCases.usuarios.impl.UsuarioServiceImpl;
import com.advogado.freelancer.useCases.usuarios.impl.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/carregue")
    @LogRest
    public ResponseEntity<List<UsuarioResponseDom>> carregarUsuarios(){
        return ResponseEntity.ok(usuarioService.carregarUsuarios());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/carregue/{id}")
    @LogRest
    public ResponseEntity<UsuarioResponseDom> carregarUsuarioById(@PathVariable Long id) throws SenacException {
        return ResponseEntity.ok(usuarioService.carregarUsuariosById(id));
    }

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
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/atualize/{id}")
    @LogRest
    public ResponseEntity<?> atualizarUsuario
            (@PathVariable Long id,
             @RequestBody UsuarioRequestDom usuarioRequestDom){
        try {
            return ResponseEntity.ok(
                    usuarioService.atualizarUsuario(id, usuarioRequestDom));
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
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok(null);
    }

    // TESTE Login --> Precisa ser um post
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/carregue/id={id}&email={email}&senha={senha}")
    @LogRest
    public ResponseEntity<Boolean> carregarUsuarioByIdEmailSenha(@PathVariable Long id, @PathVariable String email,
                                                                 @PathVariable String senha) throws SenacException {
        return ResponseEntity.ok(usuarioService.carregarUsuarioByIdEmailSenha(id, email, senha));
    }

}
