package com.advogado.freelancer.useCases.audiencia.domains;

import com.advogado.freelancer.frameWork.utils.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AudienciaResponseDom {
    private Long id;

    private Long clienteId;

    private Long processoId;

    private String data;

    private String hora;

    private String local;

    private Status status;

    private Long usuarioId;
    private String nomeCompletoUsuario;
    private String emailUsuario;
}
