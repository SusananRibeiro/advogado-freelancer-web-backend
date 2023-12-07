package com.advogado.freelancer.useCases.usuarios.impl.repositorys;

import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.entities.Processo;
import com.advogado.freelancer.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioProcessoRepository extends JpaRepository<Usuario, Long> {
    @Query("select p from processos p where p.usuarioId.id = :id")
    public List<Processo> carregarProcessoByUsuarioId(@Param("id") Long id);
}
