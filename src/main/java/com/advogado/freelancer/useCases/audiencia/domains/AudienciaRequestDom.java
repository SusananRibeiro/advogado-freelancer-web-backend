package com.advogado.freelancer.useCases.audiencia.domains;


import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.frameWork.utils.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AudienciaRequestDom {

    private Long clienteId;

    private Long processoId;

    private LocalDateTime deleted_at;

    private String data;

    private String hora;

    private String local;

    private Status status;
}
