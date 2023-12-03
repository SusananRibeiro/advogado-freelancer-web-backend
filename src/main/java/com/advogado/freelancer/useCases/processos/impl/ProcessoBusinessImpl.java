package com.advogado.freelancer.useCases.processos.impl;


import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.entities.Processo;
import com.advogado.freelancer.frameWork.annotions.Business;
import com.advogado.freelancer.frameWork.utils.SenacException;
import com.advogado.freelancer.frameWork.utils.StringUtil;
import com.advogado.freelancer.useCases.clientes.impl.repositorys.ClientesRespository;
import com.advogado.freelancer.useCases.processos.ProcessoBusiness;
import com.advogado.freelancer.useCases.processos.domains.ProcessoRequestDom;
import com.advogado.freelancer.useCases.processos.domains.ProcessoResponseDom;
import com.advogado.freelancer.useCases.processos.impl.mappers.ProcessoMapper;
import com.advogado.freelancer.useCases.processos.impl.repositorys.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
public class ProcessoBusinessImpl implements ProcessoBusiness {
    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ClientesRespository clienteRepository;

    @Override
    public List<ProcessoResponseDom> carregarProcessos() {
        List<Processo> processoList = processoRepository.findAll();

        List<ProcessoResponseDom> out = processoList
                .stream()
                .map(ProcessoMapper::processosToProcessoResponseDom)
                .collect(Collectors.toList());

        return out;
    }

    @Override
    public ProcessoResponseDom criarProcesso(ProcessoRequestDom processoRequestDom) throws Exception {

        this.validacaoManutencaoProcesso(processoRequestDom);

        var clienteRef = clienteRepository.findById(processoRequestDom.getClienteId()).get();

        Processo processo = ProcessoMapper.processoRequestDomToProcesso(processoRequestDom, clienteRef);

        Processo resultProcesso = processoRepository.save(processo);
        ProcessoResponseDom out = ProcessoMapper.processosToProcessoResponseDom(resultProcesso);
        return out;
    }

    @Override
    public ProcessoResponseDom atualizarProcesso(Long id, ProcessoRequestDom processoRequestDom) throws SenacException {
        Optional<Processo> processoRep = processoRepository.findById(id);
        Optional<Clientes> clienteRep = clienteRepository.findById(processoRequestDom.getClienteId());

        if (processoRep.isPresent()) {
            Processo processo = processoRep.get();

            // Validações
            validacaoManutencaoProcesso(processoRequestDom);

            if(clienteRep.isEmpty())
            {
                throw new SenacException("Cliente informado não encontrado!");
            }

            // Atualiza os dados
            ProcessoMapper.atualizaProcesso(processo, clienteRep.get(), processoRequestDom);

            // Salvar as alterações no banco de dados
            Processo processoAtualizado = processoRepository.save(processo);

            ProcessoResponseDom out = ProcessoMapper.processosToProcessoResponseDom(processoAtualizado);
            return out;
        } else {
            throw new SenacException("Processo informado não encontrado!");
        }
    }


    @Override
    public void deletarProcesso(Long id) throws SenacException {

        Optional<Processo> optional = processoRepository.findById(id);

        if(optional.isEmpty()) {
            throw new SenacException("Processo não encontrado");
        }

        processoRepository.deleteById(id);
    }

    @Override
    public ProcessoResponseDom carregarProcessoById(Long id) throws SenacException {
        Optional<Processo> optional = processoRepository.findById(id);

        if(optional.isEmpty()) {
            throw new SenacException("Processo não encontrado");
        }

        Processo processo = optional.get();
        ProcessoResponseDom out = ProcessoMapper.processosToProcessoResponseDom(processo);
        return out;
    }

    private void validacaoManutencaoProcesso(ProcessoRequestDom processo) throws SenacException {

        if(processo.getClienteId() == null || processo.getClienteId() == 0)
        {
            throw new SenacException("Informe um cliente!");
        }

        if(clienteRepository.findById(processo.getClienteId()).isEmpty())
        {
            throw new SenacException("Cliente não encontrado!");
        }

        if(StringUtil.validarString(processo.getNumeroProcesso())){
            throw new SenacException("Informe um número de processo!");
        }

        if (processo.getDataContrato() == null) {
            throw new SenacException("Informe uma data de contrato!");
        }

        if(StringUtil.validarString(processo.getAcaoProcesso())){
            throw new SenacException("Informe a ação do processo!");
        }

        if(StringUtil.validarString(processo.getLinkDocumento().toString())){
            throw new SenacException("Informe o endereço do documento!");
        }

        if(processo.getDataAbertura() == null){
            throw new SenacException("Informe a data de abertura!");
        }
        else if(processo.getDataFechamento() != null
                && processo.getDataFechamento().isBefore(processo.getDataAbertura()))
        {
            throw new SenacException("A data de fechamento deve ser maior que a data de abertura!");
        }

        if(processo.getStatus() == null)
        {
            throw new SenacException("Informe um status!");
        }
    }
}
