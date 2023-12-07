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

import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @CrossOrigin(origins = "http://localhost:4200")
    @LogRest
    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        String email = usuarioLoginDTO.getEmail();
        String senha = usuarioLoginDTO.getSenha();

        Usuario usuario = usuarioServiceImpl.fazerLogin(email, senha);

        if (usuario != null) {
            return ResponseEntity.ok(usuario.getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Usuário ou senha inválida."));
        }
    }

}
