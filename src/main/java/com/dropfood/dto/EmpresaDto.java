package com.dropfood.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmpresaDto(


        @NotBlank(message = "Nome da Empresa não pode ser em branco")
        @NotNull(message = "Nome da Empresa não pode ser nulo")
        @Size(max = 100)
        String nmEmpresa,

        @NotBlank(message = "Endereço não pode ser em branco")
        @NotNull(message = "Endereço não pode ser nulo")
        @Size(max = 200)
        String enderecoEmpresa,

        @NotBlank(message = "Telefone não pode ser em branco")
        @NotNull(message = "Telefone não pode ser nulo")
        @Size(max = 20)
        String telefoneEmpresa,

        @NotBlank(message = "CNPJ não pode ser em branco")
        @NotNull(message = "CNPJ não pode ser nulo")
        @Size(min = 14, max = 18, message = "CNPJ deve ter entre 14 e 18 caracteres")
        String cnpjEmpresa,

        @Size(max = 50)
        String tipoEmpresa
) {
}