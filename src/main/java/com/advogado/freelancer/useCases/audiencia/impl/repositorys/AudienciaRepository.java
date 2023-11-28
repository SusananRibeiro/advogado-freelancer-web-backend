package com.advogado.freelancer.useCases.audiencia.impl.repositorys;

import com.advogado.freelancer.entities.Audiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienciaRepository extends JpaRepository<Audiencia,Long> {
}
