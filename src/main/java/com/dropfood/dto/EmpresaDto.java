package com.dropfood.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmpresaDto(


        @NotBlank(message = "Nome da Empresa não pode ser em branco")
        @NotNull(message = "Nome da Empresa não pode ser nulo")
        @Size(max = 100)
        String nm_Empresa,

        @NotBlank(message = "Endereço não pode ser em branco")
        @NotNull(message = "Endereço não pode ser nulo")
        @Size(max = 200)
        String endereco_Empresa,

        @NotBlank(message = "Telefone não pode ser em branco")
        @NotNull(message = "Telefone não pode ser nulo")
        @Size(max = 20)
        String telefone_Empresa,

        @NotBlank(message = "CNPJ não pode ser em branco")
        @NotNull(message = "CNPJ não pode ser nulo")
        @Size(min = 14, max = 18, message = "CNPJ deve ter entre 14 e 18 caracteres")
        String cnpj_Empresa,

        @Size(max = 50)
        String tipo_Empresa
) {
}