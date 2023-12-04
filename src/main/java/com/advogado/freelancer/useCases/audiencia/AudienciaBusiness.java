package com.advogado.freelancer.useCases.audiencia;

import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaRequestDom;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaResponseDom;

import java.util.List;

public interface AudienciaBusiness {
    public AudienciaResponseDom criarAudiencia(AudienciaRequestDom audienciaRequestDom) throws Exception;

    public List<AudienciaResponseDom> carregarAudiencia();

    public AudienciaResponseDom atualizarAudiencia(Long id,AudienciaRequestDom audienciaRequestDom) throws SenacException;

    void deletarAuciencia(Long id);

    public AudienciaResponseDom carregarAudienciaById(Long id) throws SenacException;

}
