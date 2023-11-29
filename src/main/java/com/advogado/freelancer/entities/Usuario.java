package com.advogado.freelancer.entities;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;

@Data
@Entity(name = "usuarios")
@SQLDelete(sql = "UPDATE usuarios SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 8)
    private String senha;

    @Column
    private LocalDateTime deletedAt;

    // Não precisa gerar Gets e Sets, pois o "@Data" vai fazer isso

}
