package com.dropfood.dto;



public record PedidoDto(

        Double vlrTotal,

        Integer idUsuario,

        Integer idEmpresa,

        String tpPagamento,

        String stsPedido,

        String stsPagamento
) {
}