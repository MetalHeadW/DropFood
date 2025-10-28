package com.dropfood.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProdutoDto(
        @NotBlank(message = "Nome do produto não pode ser em branco")
        @Size(max = 100)
        String nmProduto,

        @Size(max = 1000)
        String dsProduto,

        @NotNull(message = "Preço não pode ser nulo")
        @DecimalMin(value = "0.01", message = "Preço deve ser maior que R$ 0,00")
        Double preco,

        @NotBlank(message = "Categoria não pode ser em branco")
        @Size(max = 50)
        String categoria,

        @NotBlank(message = "Flag Ativo não pode ser em branco")
        @Pattern(regexp = "^A|I$", message = "flg_ativo deve ser 'A' (Ativo) ou 'I' (Inativo)")
        String flgAtivo,

        @NotNull(message = "ID da Empresa não pode ser nulo")
        Integer idEmpresa
) {
}