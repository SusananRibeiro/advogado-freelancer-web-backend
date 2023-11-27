package com.advogado.freelancer.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity(name = "audiencia")
@SQLDelete(sql = "UPDATE audiencia SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class Audiencia {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clientes cliente;

    @Column
    private LocalDateTime deleted_at;

    @Column
    private String data;

    @Column
    private String hora;

    @Column
    private String local;


}
