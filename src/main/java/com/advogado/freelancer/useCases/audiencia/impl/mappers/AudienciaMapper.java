package com.advogado.freelancer.useCases.audiencia.impl.mappers;

import com.advogado.freelancer.entities.Audiencia;
import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.entities.Processo;
import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaRequestDom;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaResponseDom;
import com.advogado.freelancer.useCases.clientes.impl.mappers.ClientesMapper;
import com.advogado.freelancer.useCases.processos.impl.mappers.ProcessoMapper;

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
        out.setUsuarioId(audiencia.getUsuarioId().getId());
        out.setNomeCompletoUsuario(audiencia.getUsuarioId().getNomeCompleto());
        out.setEmailUsuario(audiencia.getUsuarioId().getEmail());

        out.setCliente(ClientesMapper.clientesToClientesResponseDom(audiencia.getCliente()));
        out.setProcesso(ProcessoMapper.processosToProcessoResponseDom(audiencia.getProcesso()));

        return out;
    }

    public static Audiencia audienciaResquestDomToAudiencia(AudienciaRequestDom audienciaRequestDom, Clientes cliente,
                                                            Processo processo, Usuario usuario){
        Audiencia out = new Audiencia();
        out.setStatus(audienciaRequestDom.getStatus());
        out.setHora(audienciaRequestDom.getHora());
        out.setData(audienciaRequestDom.getData());
        out.setCliente(cliente);
        out.setLocal(audienciaRequestDom.getLocal());
        out.setProcesso(processo);
        out.setUsuarioId(usuario);


        return out;
    }
}
