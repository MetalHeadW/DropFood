package com.dropfood.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "TBITEMPEDIDO")
public class ItemPedidoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="IDITEM")
    private Integer id_item;

    @Column(name="QUANTIDADE")
    private Integer quantidade;

    @Column(name="VALORUNITARIO")
    private Double valor_unitario;

    @Column(name="IDPEDIDO")
    private Integer id_pedido;

    @Column(name="IDPRODUTO")
    private Integer id_produto;
}