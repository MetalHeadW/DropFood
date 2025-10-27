package com.dropfood.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record ItemPedidoDto(
        @NotNull(message = "ID do Produto não pode ser nulo")
        Integer id_produto,

        @NotNull(message = "Quantidade não pode ser nula")
        @Min(value = 1, message = "Quantidade deve ser pelo menos 1")
        Integer quantidade
) {
}