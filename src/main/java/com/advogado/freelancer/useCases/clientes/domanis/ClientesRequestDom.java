package com.advogado.freelancer.useCases.clientes.domanis;
import com.advogado.freelancer.frameWork.utils.EstadosDoBrasil;
import com.advogado.freelancer.frameWork.utils.StatusAtivoInativo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientesRequestDom {

    private String nomeCompleto;

    private String cpf;

    private LocalDate dataNascimento;

    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private EstadosDoBrasil uf;

    private int cep;

    private String pais;

    private String telefone;

    private String email;

    private String complemento;

    private StatusAtivoInativo status;

    private Long usuarioId;


    // Não precisa gerar Gets, Sets e toString, pois o "@Data" vai fazer isso
}
