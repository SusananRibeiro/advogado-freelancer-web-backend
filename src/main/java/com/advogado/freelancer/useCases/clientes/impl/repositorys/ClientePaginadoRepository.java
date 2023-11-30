package com.advogado.freelancer.useCases.clientes.impl.repositorys;
import com.advogado.freelancer.entities.Clientes;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientePaginadoRepository extends PagingAndSortingRepository<Clientes, Integer> {
}
