package com.advogado.freelancer.useCases.clientes.impl.repositorys;
import com.advogado.freelancer.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteUsuarioRepository extends JpaRepository<Usuario,Long> {

}
