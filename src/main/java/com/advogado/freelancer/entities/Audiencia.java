package com.advogado.freelancer.entities;


import com.advogado.freelancer.frameWork.utils.Status;
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

    @ManyToOne
    @JoinColumn(name = "processo_id", nullable = false)
    private Processo processo;

    @Column
    private LocalDateTime deleted_at;

    @Column
    private String data;

    @Column
    private String hora;

    @Column
    private String local;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;


}
