package com.dropfood.dto;


import jakarta.persistence.ManyToOne;

public record PedidoDto(

        Double vlrTotal,
       @ManyToOne
        Integer idUsuario,
        @ManyToOne
        Integer idEmpresa,

        String tpPagamento,

        String stsPedido,

        String stsPagamento
) {
}