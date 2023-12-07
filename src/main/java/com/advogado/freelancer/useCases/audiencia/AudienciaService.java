package com.advogado.freelancer.useCases.audiencia;

import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaRequestDom;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaResponseDom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AudienciaService {
    List<AudienciaResponseDom> carregarAudiencia();

    AudienciaResponseDom criarAudiencia(AudienciaRequestDom audienciaRequestDom) throws Exception;

    AudienciaResponseDom atualizarAudiencia(Long id,AudienciaRequestDom audienciaRequestDom) throws SenacException;

    void deletarAudiencia(Long id);

    AudienciaResponseDom carregarAudienciaById(Long id) throws SenacException;
    List<AudienciaResponseDom> carregarAudienciaByUsuarioId(Long id) throws SenacException;

}
