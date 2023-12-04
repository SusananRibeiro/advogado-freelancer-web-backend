package com.advogado.freelancer.useCases.audiencia.impl.repositorys;

import com.advogado.freelancer.entities.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienciaProcessoRepository extends JpaRepository <Processo, Long> {
}
