package com.advogado.freelancer.useCases.processos;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.useCases.processos.domains.ProcessoRequestDom;
import com.advogado.freelancer.useCases.processos.domains.ProcessoResponseDom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProcessoService {
    List<ProcessoResponseDom> carregarProcessos();
    ProcessoResponseDom criarProcesso(ProcessoRequestDom processoRequestDom) throws Exception;
    ProcessoResponseDom atualizarProcesso(Long id, ProcessoRequestDom processoRequestDom) throws SenacException;
    void deletarProcesso(Long id) throws Exception;
    ProcessoResponseDom carregarProcessoById(Long id) throws SenacException;

    List<ProcessoResponseDom> carregarProcessoByUsuarioId(Long id) throws SenacException;
}
