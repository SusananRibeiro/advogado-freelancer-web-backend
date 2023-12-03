package com.advogado.freelancer.useCases.processos.impl;

import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.processos.ProcessoService;
import com.advogado.freelancer.useCases.processos.domains.ProcessoRequestDom;
import com.advogado.freelancer.useCases.processos.domains.ProcessoResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoServiceImpl implements ProcessoService {
    @Autowired
    private ProcessoBusinessImpl processoBusinessImpl;

    @Override
    public List<ProcessoResponseDom> carregarProcessos() {
        return processoBusinessImpl.carregarProcessos();
    }

    @Override
    public ProcessoResponseDom criarProcesso(ProcessoRequestDom processoRequestDom) throws Exception {
        return processoBusinessImpl.criarProcesso(processoRequestDom);
    }

    @Override
    public ProcessoResponseDom atualizarProcesso(Long id, ProcessoRequestDom processoRequestDom) throws SenacException {
        return processoBusinessImpl.atualizarProcesso(id, processoRequestDom);
    }

    @Override
    public void deletarProcesso(Long id) throws SenacException {
        processoBusinessImpl.deletarProcesso(id);
    }

    @Override
    public ProcessoResponseDom carregarProcessoById(Long id) throws SenacException {
        return processoBusinessImpl.carregarProcessoById(id);
    }
}
