package com.advogado.freelancer.useCases.processos.domains;

import com.advogado.freelancer.entities.enums.StatusProcesso;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProcessoRequestDom {
    private String numeroProcesso;
    private LocalDate dataContrato;
    private String acaoProcesso;
    private String tribunal;
    private String vara;
    private String comarca;
    private String foro;
    private StringBuilder linkDocumento;
    private LocalDate dataAbertura;
    private LocalDate dataFechamento;
    private StatusProcesso status;
    private Long clienteId;
}
