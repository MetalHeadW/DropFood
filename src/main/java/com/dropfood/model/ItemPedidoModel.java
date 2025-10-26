package com.dropfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id_item")
@Entity
@Table (name = "TBITEMPEDIDO")
public class ItemPedidoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="IDITEM")
    private Integer id_item;

    @ManyToOne
    @JoinColumn(name="IDPEDIDO", nullable = false)
    @JsonIgnore
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name="IDPRODUTO", nullable = false)
    private ProdutoModel produto;

    @Column(name="QUANTIDADE", nullable = false)
    private Integer quantidade;

    @Column(name="VALORUNITARIO", nullable = false)
    private Double valor_unitario; // Preço do produto no momento da compra

    @Column(name="VALORTOTAL", nullable = false)
    private Double valor_total; // (Quantidade * Valor Unitário)
}