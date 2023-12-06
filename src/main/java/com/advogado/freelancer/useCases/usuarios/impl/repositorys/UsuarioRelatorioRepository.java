package com.advogado.freelancer.useCases.usuarios.impl.repositorys;
import com.advogado.freelancer.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRelatorioRepository extends JpaRepository<Usuario, Long> {
    public boolean existsByEmail(String email);
    public Usuario findByEmailAndSenha(String email, String senha);


}
