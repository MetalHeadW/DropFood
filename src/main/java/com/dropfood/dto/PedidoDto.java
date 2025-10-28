package com.dropfood.dto;

import jakarta.validation.constraints.DecimalMin;

public record PedidoDto(
        @DecimalMin(value = "0.01", message = "Valor Total deve ser positivo")
        Double vlrTotal,

        Integer idUsuario,

        Integer idEmpresa,

        String tpPagamento,

        String stsPedido,

        String stsPagamento
) {
}