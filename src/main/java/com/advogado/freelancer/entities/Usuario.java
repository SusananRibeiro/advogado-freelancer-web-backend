package com.advogado.freelancer.entities;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(nullable = false, length = 8)
    private String confirmaSenha;

    @Column
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "usuarioId") // precisa ter o mesmo nome que está na entidade Cliente
    private List<Clientes> clientes;

    @OneToMany(mappedBy = "usuarioId") // precisa ter o mesmo nome que está na entidade Cliente
    private List<Processo> processos;

    @OneToMany(mappedBy = "usuarioId") // precisa ter o mesmo nome que está na entidade Cliente
    private List<Audiencia> audiencia;

    // Não precisa gerar Gets e Sets, pois o "@Data" vai fazer isso

}
