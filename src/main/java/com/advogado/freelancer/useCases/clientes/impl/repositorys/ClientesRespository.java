package com.advogado.freelancer.useCases.clientes.impl.repositorys;
import com.advogado.freelancer.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRespository extends JpaRepository<Clientes, Long> {

}
