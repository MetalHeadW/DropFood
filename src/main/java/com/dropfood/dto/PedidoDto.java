package com.dropfood.dto;

import jakarta.validation.constraints.DecimalMin;

public record PedidoDto(
        @DecimalMin(value = "0.01", message = "Valor Total deve ser positivo")
        Double vlr_Total,

        Integer id_Usuario,

        Integer id_Empresa,

        String tp_Pagamento,

        String sts_Pedido,

        String sts_Pagamento
) {
}