package com.advogado.freelancer.useCases.audiencia.impl;

import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.audiencia.AudienciaService;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaRequestDom;
import com.advogado.freelancer.useCases.audiencia.domains.AudienciaResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudienciaServiceImpl implements AudienciaService {

    @Autowired
    private AudienciaBusinessImpl audienciaBusiness;

    @Override
    public List<AudienciaResponseDom> carregarAudiencia() {
        return audienciaBusiness.carregarAudiencia();
    }

    @Override
    public AudienciaResponseDom criarAudiencia(AudienciaRequestDom audienciaRequestDom) throws Exception {
        return audienciaBusiness.criarAudiencia(audienciaRequestDom);
    }

    @Override
    public AudienciaResponseDom atualizarAudiencia(Long id, AudienciaRequestDom audienciaRequestDom) throws SenacException {
        return audienciaBusiness.atualizarAudiencia(id,audienciaRequestDom);
    }

    @Override
    public void deletarAudiencia(Long id) {
        audienciaBusiness.deletarAuciencia(id);
    }

    @Override
    public AudienciaResponseDom carregarAudienciaById(Long id) throws SenacException {
        return audienciaBusiness.carregarAudienciaById(id);
    }
}
