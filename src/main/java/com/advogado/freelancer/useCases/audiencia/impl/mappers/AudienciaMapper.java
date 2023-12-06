package com.advogado.freelancer.useCases.audiencia.impl.mappers;

import com.advogado.freelancer.entities.Audiencia;
import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.entities.Processo;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaRequestDom;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaResponseDom;

import java.util.Optional;

public class AudienciaMapper {
    public static AudienciaResponseDom audienciaToAudienciaResponseDom(Audiencia audiencia){
        AudienciaResponseDom out = new AudienciaResponseDom();

        out.setId(audiencia.getId());
        out.setStatus(audiencia.getStatus());
        out.setHora(audiencia.getHora());
        out.setData(audiencia.getData());
        out.setClienteId(audiencia.getCliente().getId());
        out.setLocal(audiencia.getLocal());
        out.setProcessoId(audiencia.getProcesso().getId());

        return out;
    }

    public static Audiencia audienciaResquestDomToAudiencia(AudienciaRequestDom audienciaRequestDom, Clientes cliente, Processo processo){
        Audiencia out = new Audiencia();

        out.setStatus(audienciaRequestDom.getStatus());
        out.setHora(audienciaRequestDom.getHora());
        out.setData(audienciaRequestDom.getData());
        out.setCliente(cliente);
        out.setLocal(audienciaRequestDom.getLocal());
        out.setProcesso(processo);

        return out;
    }
}
