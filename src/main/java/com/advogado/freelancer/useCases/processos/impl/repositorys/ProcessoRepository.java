package com.advogado.freelancer.useCases.processos.impl.repositorys;

import com.advogado.freelancer.entities.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
}
