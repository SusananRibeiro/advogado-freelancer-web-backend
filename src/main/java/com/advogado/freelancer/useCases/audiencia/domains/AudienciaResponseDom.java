package com.advogado.freelancer.useCases.audiencia.domains;

import com.advogado.freelancer.frameWork.utils.Status;
import com.advogado.freelancer.useCases.clientes.domanis.ClientesResponseDom;
import com.advogado.freelancer.useCases.processos.domains.ProcessoResponseDom;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AudienciaResponseDom {
    private Long id;

    private Long clienteId;

    private Long processoId;

    private LocalDateTime deleted_at;

    private String data;

    private String hora;

    private String local;

    private Status status;

    private ClientesResponseDom cliente;

    private ProcessoResponseDom processo;

    private Long usuarioId;
    private String nomeCompletoUsuario;
    private String emailUsuario;
}
