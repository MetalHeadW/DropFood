package com.dropfood.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_produto")
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

    @Column(name = "CATEGORIA", length = 50, nullable = false)
    private String categoria;

    @Column(name = "FLGATIVO", length = 1, nullable = false)
    private String flg_ativo;

    @ManyToOne
    @JoinColumn(name = "IDEMPRESA", nullable = false)
    private EmpresaModel empresa;
}