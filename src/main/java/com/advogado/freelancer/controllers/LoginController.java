package com.advogado.freelancer.controllers;

import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.frameWork.annotions.LogRest;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioLoginDTO;
import com.advogado.freelancer.useCases.usuarios.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @CrossOrigin(origins = "http://localhost:4200")
    @LogRest
    @PostMapping("/login")
    public ResponseEntity<String> fazerLogin(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        String email = usuarioLoginDTO.getEmail();
        String senha = usuarioLoginDTO.getSenha();

        Usuario usuario = usuarioServiceImpl.fazerLogin(email, senha);

        if (usuario != null) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            // "HttpStatus.UNAUTHORIZED" indica que a requisição não foi aplicada porque não possui credenciais de autenticação válidas para o recurso solicitado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválida.");
        }
    }


}
