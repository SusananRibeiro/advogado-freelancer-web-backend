package com.advogado.freelancer.entities;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity(name = "clientes")
@SQLDelete(sql = "UPDATE clientes SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at is null")
public class Clientes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;
    @Column(name = "cpf_ou_cnpj", nullable = false)
    private String cpfOuCnpj;
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;
    @Column
    private String rua;
    @Column
    private int numero;
    @Column
    private String bairro;
    @Column
    private String uf;
    @Column
    private int cep;
    @Column
    private String pais;
    @Column
    private String telefone;
    @Column
    private String email;
    @Column
    private String complemento;
    @Column
    private boolean status;
    @Column
    private LocalDateTime deletedAt;

    // Não precisa gerar Gets e Sets, pois o "@Data" vai fazer isso

   // rua, numero, bairro, uf, cep, pais, telefone, email, complemento, status

}
