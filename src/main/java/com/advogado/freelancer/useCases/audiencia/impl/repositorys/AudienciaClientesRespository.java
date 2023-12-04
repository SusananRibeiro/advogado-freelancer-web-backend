package com.advogado.freelancer.useCases.audiencia.impl.repositorys;

import com.advogado.freelancer.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienciaClientesRespository extends JpaRepository<Clientes,Long> {
}
