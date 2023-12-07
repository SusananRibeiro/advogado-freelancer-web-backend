package com.advogado.freelancer.useCases.clientes.impl.repositorys;
import com.advogado.freelancer.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRelatorioRepository extends JpaRepository<Clientes, Long> {
    public boolean existsByCpf(String cpf);

}
