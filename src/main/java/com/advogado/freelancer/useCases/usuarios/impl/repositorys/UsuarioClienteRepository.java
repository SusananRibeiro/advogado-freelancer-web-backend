package com.advogado.freelancer.useCases.usuarios.impl.repositorys;
import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioClienteRepository extends JpaRepository<Usuario, Long> {
    @Query("select c from clientes c where c.usuarioId.id = :id")
    public List<Clientes> carregarClientesByUsuarioId(@Param("id") Long id);

}
