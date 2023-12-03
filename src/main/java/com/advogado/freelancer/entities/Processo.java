package com.advogado.freelancer.entities;

import com.advogado.freelancer.entities.enums.StatusProcesso;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(name = "processos")
@SQLDelete(sql = "UPDATE processos SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class Processo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_processo", nullable = false)
    private String numeroProcesso;

    @Column(name = "data_contrato", nullable = false)
    private LocalDate dataContrato;

    @Column(name = "acao_processo", nullable = false)
    private String acaoProcesso;

    @Column(name = "tribunal")
    private String tribunal;

    @Column(name = "vara")
    private String vara;

    @Column(name = "comarca")
    private String comarca;

    @Column(name = "foro")
    private String foro;

    @Column(name = "link_documento", nullable = false)
    private StringBuilder linkDocumento;

    @Column(name = "data_abertura", nullable = false)
    private LocalDate dataAbertura;

    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    @Enumerated(EnumType.STRING)
    private StatusProcesso status;

    @Column
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Clientes cliente;
}