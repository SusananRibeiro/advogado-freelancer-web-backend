package com.advogado.freelancer.useCases.usuarios.impl.repositorys;
import com.advogado.freelancer.entities.Audiencia;
import com.advogado.freelancer.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UsuarioAudienciaRepository extends JpaRepository<Usuario, Long> {
    @Query("select a from audiencia a where a.usuarioId.id = :id")
    public List<Audiencia> carregarAudienciaByUsuarioId(@Param("id") Long id);
}
