package com.advogado.freelancer.useCases.processos.impl.mappers;
import com.advogado.freelancer.entities.Clientes;
import com.advogado.freelancer.entities.Processo;
import com.advogado.freelancer.entities.Usuario;
import com.advogado.freelancer.useCases.clientes.impl.mappers.ClientesMapper;
import com.advogado.freelancer.useCases.processos.domains.ProcessoRequestDom;
import com.advogado.freelancer.useCases.processos.domains.ProcessoResponseDom;

public class ProcessoMapper {
    public static Processo processoRequestDomToProcesso(ProcessoRequestDom processoRequestDom, Clientes cliente, Usuario usuario){

        Processo out = new Processo();

        out.setNumeroProcesso(processoRequestDom.getNumeroProcesso());
        out.setDataContrato(processoRequestDom.getDataContrato());
        out.setAcaoProcesso(processoRequestDom.getAcaoProcesso());
        out.setTribunal(processoRequestDom.getTribunal());
        out.setVara(processoRequestDom.getVara());
        out.setComarca(processoRequestDom.getComarca());
        out.setForo(processoRequestDom.getForo());
        out.setLinkDocumento(processoRequestDom.getLinkDocumento());
        out.setDataAbertura(processoRequestDom.getDataAbertura());
        out.setDataFechamento(processoRequestDom.getDataFechamento());
        out.setStatus(processoRequestDom.getStatus());

        out.setCliente(cliente);
        out.setUsuarioId(usuario);

        return out;
    }

    public static ProcessoResponseDom processosToProcessoResponseDom(Processo processo){

        ProcessoResponseDom out = new ProcessoResponseDom();

        out.setId(processo.getId());
        out.setNumeroProcesso(processo.getNumeroProcesso());
        out.setDataContrato(processo.getDataContrato());
        out.setAcaoProcesso(processo.getAcaoProcesso());
        out.setTribunal(processo.getTribunal());
        out.setVara(processo.getVara());
        out.setComarca(processo.getComarca());
        out.setForo(processo.getForo());
        out.setLinkDocumento(processo.getLinkDocumento());
        out.setDataAbertura(processo.getDataAbertura());
        out.setDataFechamento(processo.getDataFechamento());
        out.setStatus(processo.getStatus());

        out.setUsuarioId(processo.getUsuarioId().getId());
        out.setNomeCompletoUsuario(processo.getUsuarioId().getNomeCompleto());
        out.setEmailUsuario(processo.getUsuarioId().getEmail());
        out.setSenhaUsuario(processo.getUsuarioId().getSenha());
        out.setConfirmaSenhaUsuario(processo.getUsuarioId().getConfirmaSenha());

        if (processo.getCliente() != null)
        {
            out.setClienteId(processo.getCliente().getId());
            out.setCliente(ClientesMapper.clientesToClientesResponseDom(processo.getCliente()));
        }

        return out;
    }

    public static void atualizaProcesso(Processo processo, Clientes cliente, ProcessoRequestDom processoRequestDom){

        processo.setNumeroProcesso(processoRequestDom.getNumeroProcesso());
        processo.setDataContrato(processoRequestDom.getDataContrato());
        processo.setAcaoProcesso(processoRequestDom.getAcaoProcesso());
        processo.setTribunal(processoRequestDom.getTribunal());
        processo.setVara(processoRequestDom.getVara());
        processo.setComarca(processoRequestDom.getComarca());
        processo.setForo(processoRequestDom.getForo());
        processo.setLinkDocumento(processoRequestDom.getLinkDocumento());
        processo.setDataAbertura(processoRequestDom.getDataAbertura());
        processo.setDataFechamento(processoRequestDom.getDataFechamento());
        processo.setStatus(processoRequestDom.getStatus());

        processo.setCliente(cliente);
    }
}
