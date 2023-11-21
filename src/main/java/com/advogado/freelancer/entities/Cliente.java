package com.advogado.freelancer.entities;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;

@Data
@Entity(name = "clientes")
@SQLDelete(sql = "UPDATE clientes SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class Cliente {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private LocalDateTime deletedAt;

}
