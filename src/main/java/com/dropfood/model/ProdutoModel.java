package com.dropfood.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBPRODUTO")

public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPRODUTO")
    private Integer id_produto;

    @Column(name = "NMPRODUTO", length = 100, nullable = false)
    private String nm_produto;

    @Column(name = "DSPRODUTO", length = 1000)
    private String ds_produto;

    @Column(name = "PRECO", nullable = false)
    private Double preco;

    @Column(name = "CATEGORIA", nullable = false)
    private String categoria;

    @Column(name = "FLGATIVO", nullable = false)
    private String flg_ativo;

    @Column(name = "IDEMPRESA")
    private Integer id_empresa;
}