package com.advogado.freelancer.useCases.audiencia.impl.repositorys;

import com.advogado.freelancer.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienciaUsuarioRepository extends JpaRepository<Usuario,Long> {
}
