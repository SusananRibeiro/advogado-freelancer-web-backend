package com.advogado.freelancer.useCases.usuarios.impl.repositorys;
import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.useCases.usuarios.domanis.UsuarioRequestDom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRelatorioRepository extends JpaRepository<Usuario, Long> {
    public boolean existsByEmail(String email);


}
